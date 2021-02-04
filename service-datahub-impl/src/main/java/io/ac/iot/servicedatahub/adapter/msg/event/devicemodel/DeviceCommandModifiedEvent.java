package io.ac.iot.servicedatahub.adapter.msg.event.devicemodel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-23
 **/
@Getter
public class DeviceCommandModifiedEvent extends BaseDeviceModelEvent {

    private final String commandList;

    @JsonCreator
    protected DeviceCommandModifiedEvent(
            @JsonProperty String _id,
            @JsonProperty String _type,
            @JsonProperty Date _createdAt,
            @JsonProperty String modelId,
            @JsonProperty String commandList) {
        super(_id, _type, _createdAt,modelId);
        this.commandList = commandList;
    }
}
