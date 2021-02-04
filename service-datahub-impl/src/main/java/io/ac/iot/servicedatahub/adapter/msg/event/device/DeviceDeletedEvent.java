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
public class DeviceDeletedEvent extends AbstractDeviceEvent {

    @JsonCreator
    public DeviceDeletedEvent(
            @JsonProperty("_id")String _id,
            @JsonProperty("_type")String _type,
            @JsonProperty("_createAt")Date _createdAt,
            @JsonProperty("uniqueCode")String uniqueCode,
            @JsonProperty("tenantId")String tenantId,
            @JsonProperty("tenantId")String deviceId) {
        super(_id, _type, _createdAt,uniqueCode,tenantId,deviceId);
    }
}
