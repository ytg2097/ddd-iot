package io.ac.iot.servicedatahub.adapter.msg.event.device;

import io.ac.starter.DomainEvent;
import lombok.Getter;

import java.util.Date;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-16
 **/
@Getter
public abstract class AbstractDeviceEvent extends DomainEvent {

    private String uniqueCode;

    private String tenantId;

    private String deviceId;

    public AbstractDeviceEvent (String id,
                        String type,
                        Date createAt,
                        String uniqueCode,
                        String tenantId,
                        String deviceId){
        super(id,type,createAt);
        this.uniqueCode = uniqueCode;
        this.tenantId = tenantId;
        this.deviceId = deviceId;
    }
}
