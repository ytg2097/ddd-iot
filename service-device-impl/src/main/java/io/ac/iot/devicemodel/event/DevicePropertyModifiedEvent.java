package io.ac.iot.devicemodel.event;

import common.JsonUtil;
import io.ac.iot.devicemodel.model.capability.DeviceCapability;
import io.ac.starter.DomainEvent;
import lombok.Getter;

import static io.ac.iot.devicemodel.event.DeviceCapabilityEventEnum.DEVICE_PROPERTY_MODIFIED;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-23
 **/
@Getter
public class DevicePropertyModifiedEvent extends DomainEvent {

    private final String deviceModelId;

    private final String properties;

    private final Integer deviceCapabilityId;

    public DevicePropertyModifiedEvent(DeviceCapability deviceCapability) {
        super(DEVICE_PROPERTY_MODIFIED);
        this.deviceModelId = deviceCapability.getModelId();
        this.properties = JsonUtil.toJson(deviceCapability.getProperties());
        this.deviceCapabilityId = deviceCapability.getId();
    }
}
