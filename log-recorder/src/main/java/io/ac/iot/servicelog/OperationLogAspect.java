package io.ac.iot.servicelog;

import com.alibaba.fastjson.JSONObject;
import common.NetworkUtil;
import io.ac.starter.util.RequestHeadHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

import static io.ac.starter.util.RequestHeadHolder.RequestHead.LOGIN_NAME;
import static io.ac.starter.util.RequestHeadHolder.RequestHead.TENANT_ID;
import static java.util.Objects.nonNull;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-23
 **/
@Aspect
@Component
@Slf4j
public class OperationLogAspect {

    private ThreadLocal<TenantOperationLogCreateCommand> commandStorage = new ThreadLocal<>();
    private ThreadLocal<LogPropertiesHolder> propertiesHolderThreadLocal = new ThreadLocal<>();


    @Autowired
    private BinderAwareChannelResolver resolver;

    @Before("@annotation(io.ac.iot.servicelog.OperationLog)")
    public void doBefore(JoinPoint point) {

        try {

            propertiesHolderThreadLocal.set(new LogPropertiesHolder(point));
            commandStorage.set(create(point));
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

    @AfterReturning(value = "@annotation(io.ac.iot.servicelog.OperationLog)",returning = "r")
    public void doAfterReturning(Object r) {

        try {
            TenantOperationLogCreateCommand command = commandStorage.get();
            commandStorage.remove();
            if(nonNull(command)){
                command.setResult("SUCCESS");
                command.setDetail("");
                LogPropertiesHolder propertiesHolder = propertiesHolderThreadLocal.get();
                boolean annotationPresent = propertiesHolder.getTargetMethod().isAnnotationPresent(Login.class);
                if (annotationPresent){

                    JSONObject loginDTO = JSONObject.parseObject(JSONObject.toJSONString(r)).getJSONObject("result");
                    command.setTenantId(loginDTO.getString("tenantId"));
                    command.setLoginName(loginDTO.getString("loginName"));
                }else{
                    command.setLoginName(RequestHeadHolder.get(LOGIN_NAME));
                    command.setTenantId(RequestHeadHolder.get(TENANT_ID));
                }
                propertiesHolderThreadLocal.remove();
                send(command);
            }
        }catch (Exception e){

            log.error(e.getMessage());
        }

    }

    @AfterThrowing(value = "@annotation(io.ac.iot.servicelog.OperationLog)",throwing = "e")
    public void doAfterThrowing(Throwable e) {

        try{
            TenantOperationLogCreateCommand command = commandStorage.get();
            commandStorage.remove();
            propertiesHolderThreadLocal.remove();
            if(nonNull(command)){
                command.setResult("FAILURE");
                command.setDetail("");
                send(command);
            }
        }catch (Exception ex){
            log.error(ex.getMessage());
        }
    }

    private TenantOperationLogCreateCommand create(JoinPoint point){

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        LogPropertiesHolder propertiesHolder = propertiesHolderThreadLocal.get();
        TenantOperationLogCreateCommand command = new TenantOperationLogCreateCommand();
        command.setIp(NetworkUtil.getIpAddress(request));
        command.setCreateTime(new Date());
        command.setType(propertiesHolder.getType());
        command.setTarget(propertiesHolder.getTarget());
        command.setOperating(propertiesHolder.getOperating());
        return command;
    }

    private void send(TenantOperationLogCreateCommand command) {

        resolver.resolveDestination("tenant-operation-log").send(
                MessageBuilder
                .withPayload(command)
                .build()
        );
    }

}
