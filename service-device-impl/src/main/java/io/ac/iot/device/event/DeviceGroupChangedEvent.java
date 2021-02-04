package io.ac.iot.device.event;

import io.ac.iot.device.model.Device;
import io.ac.starter.DomainEvent;
import lombok.Getter;

import static io.ac.iot.device.event.DeviceEventEnum.DEVICE_MODIFIED;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-24
 **/
@Getter
public class DeviceGroupChangedEvent extends DomainEvent {

    private final String uniqueCode;

    private final String tenantId;

    private final String deviceId;

    private final String groupId;

    public DeviceGroupChangedEvent(Device device){
        super(DEVICE_MODIFIED);
        this.uniqueCode = device.getUniqueCode();
        this.tenantId = device.getTenantId();
        this.deviceId = device.getId();
        this.groupId = device.getDeviceGroupId();
    }
}
