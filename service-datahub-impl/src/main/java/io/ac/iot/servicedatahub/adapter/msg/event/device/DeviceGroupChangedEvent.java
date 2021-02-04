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
public class DeviceGroupChangedEvent extends AbstractDeviceEvent {

    private final String groupId;

    @JsonCreator
    public DeviceGroupChangedEvent(
            @JsonProperty("_id") String _id,
            @JsonProperty("_type")String _type,
            @JsonProperty("_createAt")Date _createdAt,
            @JsonProperty("uniqueCode")String uniqueCode,
            @JsonProperty("tenantId")String tenantId,
            @JsonProperty("groupId")String groupId,
            @JsonProperty("deviceId")String deviceId) {
        super(_id, _type, _createdAt,uniqueCode,tenantId,deviceId);
        this.groupId = groupId;
    }

}
