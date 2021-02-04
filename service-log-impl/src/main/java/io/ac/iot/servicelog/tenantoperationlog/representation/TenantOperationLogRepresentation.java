package io.ac.iot.servicelog.tenantoperationlog.representation;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.ac.iot.servicelog.tenantoperationlog.model.OperationLogType;
import io.ac.iot.servicelog.tenantoperationlog.model.OperationResult;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-23
 **/
@Getter
@Setter
public class TenantOperationLogRepresentation {

    private String loginName;

    private String ip;

    private String type;

    private String result;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    private String detail;

    private String operating;

    private String target;
}
