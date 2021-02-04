package io.ac.iot.servicedatahub.adapter.msg.event.device;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-16
 **/
@Getter
public class DeviceCreatedEvent extends AbstractDeviceEvent {

    private final String modelId;

    @JsonCreator
    public DeviceCreatedEvent(
            @JsonProperty("_id") String id,
            @JsonProperty("_type") String type,
            @JsonProperty("_createAt") Date createAt,
            @JsonProperty("uniqueCode") String uniqueCode,
            @JsonProperty("tenantId") String tenantId,
            @JsonProperty("modelId") String modelId,
            @JsonProperty("deviceId") String deviceId
            ) {
        super(id, type, createAt, uniqueCode, tenantId,deviceId);
        this.modelId = modelId;
    }
}
