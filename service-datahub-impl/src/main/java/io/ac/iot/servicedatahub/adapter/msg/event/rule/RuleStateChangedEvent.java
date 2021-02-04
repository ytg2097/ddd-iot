package io.ac.iot.servicedatahub.adapter.msg.event.rule;

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
public class RuleStateChangedEvent extends BaseRuleEvent{

    private final Boolean state;

    @JsonCreator
    public RuleStateChangedEvent(
            @JsonProperty String _id,
            @JsonProperty String _type,
            @JsonProperty Date _createdAt,
            @JsonProperty String engineId,
            @JsonProperty Boolean state) {
        super(_id, _type, _createdAt, engineId);
        this.state = state;
    }
}
