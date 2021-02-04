package io.ac.iot.servicedatahub.adapter.msg.event.rule;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-18
 **/
@Getter
public class RuleModifiedEvent extends BaseRuleEvent {

    private final Boolean mon;

    private final Boolean tue;

    private final Boolean wed;

    private final Boolean thu;

    private final Boolean fri;

    private final Boolean sat;

    private final Boolean sun;

    private final String startTime;

    private final String endTime;

    private final String deviceModelId;

    private final String deviceGroupId;

    private final List<String> deviceIds;

    private final String expression;

    @JsonCreator
    public RuleModifiedEvent(
            @JsonProperty("_id") String _id,
            @JsonProperty("_type") String _type,
            @JsonProperty("_createdAt") Date _createdAt,
            @JsonProperty("ruleId") String ruleId,
            @JsonProperty("mon") Boolean mon,
            @JsonProperty("tue") Boolean tue,
            @JsonProperty("wed") Boolean wed,
            @JsonProperty("thu") Boolean thu,
            @JsonProperty("fri") Boolean fri,
            @JsonProperty("sat") Boolean sat,
            @JsonProperty("sun") Boolean sun,
            @JsonProperty("startTime") String startTime,
            @JsonProperty("endTime") String endTime,
            @JsonProperty("deviceModelId") String deviceModelId,
            @JsonProperty("deviceGroupId") String deviceGroupId,
            @JsonProperty("deviceIds") List<String> deviceIds,
            @JsonProperty("expression") String expression) {
        super(_id, _type, _createdAt, ruleId);
        this.mon = mon;
        this.tue = tue;
        this.wed = wed;
        this.thu = thu;
        this.fri = fri;
        this.sat = sat;
        this.sun = sun;
        this.startTime = startTime;
        this.endTime = endTime;
        this.deviceModelId = deviceModelId;
        this.deviceGroupId = deviceGroupId;
        this.deviceIds = deviceIds;
        this.expression = expression;
    }
}
