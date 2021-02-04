package io.ac.iot.servicelog.tenantoperationlog.model;

import common.util.ObjectUtil;
import io.ac.iot.servicelog.tenantoperationlog.event.TenantOperationFinishEvent;
import io.ac.iot.servicelog.tenantoperationlog.representation.TenantOperationLogRepresentation;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

import static common.util.UUIDGenerator.gen32;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-22
 **/
@Data
@Document(collection = "tenant_operation_log")
public class TenantOperationLog {

    @Id
    private String id;

    private String loginName;

    private String tenantId;

    private String ip;

    private OperationLogType type;

    private OperationResult result;

    private Date createTime;

    private String detail;

    private String operating;

    private String target;

    public static TenantOperationLog of(TenantOperationFinishEvent createCommand) {

        TenantOperationLog log = new TenantOperationLog();
        log.setCreateTime(createCommand.getCreateTime());
        log.setDetail(createCommand.getDetail());
        log.setIp(createCommand.getIp());
        log.setId(gen32());
        log.setLoginName(createCommand.getLoginName());
        log.setTenantId(createCommand.getTenantId());
        log.setResult(OperationResult.valueOf(createCommand.getResult()));
        log.setType(OperationLogType.valueOf(createCommand.getType()));
        log.setTarget(createCommand.getTarget());
        log.setOperating(createCommand.getOperating());
        return log;
    }

    public TenantOperationLogRepresentation toRepresentation(){

        TenantOperationLogRepresentation result = new TenantOperationLogRepresentation();
        result.setCreateTime(createTime);
        result.setDetail(detail);
        result.setIp(ip);
        result.setResult(this.result.name());
        result.setType(type.name());
        result.setLoginName(loginName);
        result.setOperating(operating);
        result.setTarget(target);
        return result;
    }
}
