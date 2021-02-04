package io.ac.iot.servicelog.tenantoperationlog.event;

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
public class TenantOperationFinishEvent {

    private String loginName;

    private String tenantId;

    private String ip;

    private String type;

    private String result;

    private Date createTime;

    private String detail;

    private String operating;

    private String target;
}
