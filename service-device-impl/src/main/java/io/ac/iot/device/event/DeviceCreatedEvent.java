package io.ac.iot.device.event;

import io.ac.iot.device.model.Device;
import io.ac.starter.DomainEvent;
import lombok.Getter;

import static io.ac.iot.device.event.DeviceEventEnum.DEVICE_CREATED;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-24
 **/
@Getter
public class DeviceCreatedEvent extends DomainEvent {

    private final String tenantId;

    private final String groupId;

    private final String uniqueCode;

    private final String deviceId;

    public DeviceCreatedEvent(Device device) {
        super(DEVICE_CREATED);
        this.tenantId = device.getTenantId();
        this.groupId = device.getDeviceGroupId();
        this.uniqueCode = device.getUniqueCode();
        this.deviceId = device.getId();
    }
}
