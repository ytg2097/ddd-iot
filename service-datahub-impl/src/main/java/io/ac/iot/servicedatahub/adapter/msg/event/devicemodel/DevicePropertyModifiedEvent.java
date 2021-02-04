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
public class DevicePropertyModifiedEvent extends BaseDeviceModelEvent {

    private final String properties;

    @JsonCreator
    protected DevicePropertyModifiedEvent(
            @JsonProperty String _id,
            @JsonProperty String _type,
            @JsonProperty Date _createdAt,
            @JsonProperty String modelId,
            @JsonProperty String properties) {
        super(_id, _type, _createdAt,modelId);
        this.properties = properties;
    }
}
