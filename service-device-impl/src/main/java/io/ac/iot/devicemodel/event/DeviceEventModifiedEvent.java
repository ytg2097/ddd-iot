package io.ac.iot.devicemodel.event;

import common.JsonUtil;
import io.ac.iot.devicemodel.model.capability.DeviceCapability;
import io.ac.starter.DomainEvent;
import lombok.Getter;

import static io.ac.iot.devicemodel.event.DeviceCapabilityEventEnum.DEVICE_EVENT_MODIFIED;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-23
 **/
@Getter
public class DeviceEventModifiedEvent extends DomainEvent {

    private final String deviceModelId;

    private final String deviceEventList;

    private final Integer deviceCapabilityId;

    public DeviceEventModifiedEvent(DeviceCapability deviceCapability) {
        super(DEVICE_EVENT_MODIFIED);
        this.deviceModelId = deviceCapability.getModelId();
        this.deviceEventList = JsonUtil.toJson(deviceCapability.getEvents());
        this.deviceCapabilityId = deviceCapability.getId();
    }
}
