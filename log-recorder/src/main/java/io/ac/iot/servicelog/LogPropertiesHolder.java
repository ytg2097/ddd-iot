package io.ac.iot.servicelog;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import org.aspectj.lang.JoinPoint;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.stream.Stream;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-24
 **/
@Getter
public class LogPropertiesHolder {

    private final String type;

    private final String target;

    private final String operating;

    private final Class targetClass;

    private final Method targetMethod;

    private final OperationLog operationLog;

    private final JoinPoint point;

    public LogPropertiesHolder(JoinPoint point){

        this.point = point;
        this.targetClass = point.getTarget().getClass();
        String targetMethod = point.getSignature().getName();
        this.targetMethod = Stream.of(targetClass.getMethods()).filter(method -> method.getName().equals(targetMethod)).findFirst().get();
        this.operationLog = this.targetMethod.getAnnotation(OperationLog.class);
        this.type = this.operationLog.type().name();
        this.operating = this.operationLog.operating();
        this.target = target();
    }

    private String target(){

        switch (operationLog.target().origin()){

            case METHOD_ARGS:

                return args();
            case CUSTOMIZE:

                return operationLog.target().name();

            case REQUEST_HEAD:

                return header();
            case REQUEST_PARAM:

                return param();
            default:

                return null;
        }
    }

    private String args(){

        Parameter[] parameters = targetMethod.getParameters();
        String[] split = operationLog.target().name().split("\\.");

        String arg = null;
        for (int i = 0; i < parameters.length; i++) {

            if (parameters[i].getName().equals(split[0])){
                arg = point.getArgs()[i].toString();
                break;
            }
        }

        if (arg == null){

            return null;
        }

        if (split.length == 1){
            return arg.toString();
        }
        JSONObject jsonObject = JSONObject.parseObject(arg);
        for (int i = 1; i < split.length - 1; i++) {
            jsonObject = JSONObject.parseObject(split[i]);
        }
        return jsonObject.getString(split[split.length - 1]);
    }

    private String param(){

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getParameter(operationLog.target().name());
    }

    private String header(){

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader(operationLog.target().name());
    }


}
