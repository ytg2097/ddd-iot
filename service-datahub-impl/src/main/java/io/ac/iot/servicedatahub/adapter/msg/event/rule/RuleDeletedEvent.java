package io.ac.iot.servicedatahub.adapter.msg.event.rule;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Date;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-18
 **/
@Getter
public class RuleDeletedEvent extends BaseRuleEvent{

    @JsonCreator
    public RuleDeletedEvent(
            @JsonProperty String _id,
            @JsonProperty String _type,
            @JsonProperty Date _createdAt,
            @JsonProperty String ruleId) {
        super(_id, _type, _createdAt, ruleId);
    }
}
