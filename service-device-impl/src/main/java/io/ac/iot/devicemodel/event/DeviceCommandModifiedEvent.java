package io.ac.iot.devicemodel.event;

import common.JsonUtil;
import io.ac.iot.devicemodel.model.capability.DeviceCapability;
import io.ac.starter.DomainEvent;
import lombok.Getter;
import lombok.Setter;

import static io.ac.iot.devicemodel.event.DeviceCapabilityEventEnum.DEVICE_COMMAND_MODIFIED;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-23
 **/
@Getter
public class DeviceCommandModifiedEvent extends DomainEvent {

    private final String deviceModelId;

    private final String commandList;

    private final Integer deviceCapabilityId;

    public DeviceCommandModifiedEvent(DeviceCapability deviceCapability) {

        super(DEVICE_COMMAND_MODIFIED);
        this.deviceModelId = deviceCapability.getModelId();
        this.commandList = JsonUtil.toJson(deviceCapability.getCommands());
        this.deviceCapabilityId = deviceCapability.getId();
    }
}
