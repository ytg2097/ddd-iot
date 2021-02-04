package io.ac.iot.device.event;

import io.ac.iot.device.model.Device;
import io.ac.starter.DomainEvent;
import lombok.Getter;

import static io.ac.iot.device.event.DeviceEventEnum.DEVICE_DELETED;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-24
 **/
@Getter
public class DeviceDeletedEvent extends DomainEvent {

    private final String deviceId;

    public DeviceDeletedEvent(Device device){

        super(DEVICE_DELETED);
        this.deviceId = device.getId();
    }
}
