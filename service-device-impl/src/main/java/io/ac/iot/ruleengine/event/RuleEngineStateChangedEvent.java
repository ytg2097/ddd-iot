package io.ac.iot.ruleengine.event;

import io.ac.iot.ruleengine.entity.RuleEngine;
import io.ac.starter.DomainEvent;
import lombok.Getter;

import static io.ac.iot.ruleengine.event.RuleEngineEngineEventEnum.RULE_STATE_CHANGED;


/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-23
 **/
@Getter
public class RuleEngineStateChangedEvent extends DomainEvent {

    private String engineId;

    private Boolean state;

    public RuleEngineStateChangedEvent(RuleEngine rule) {
        super(RULE_STATE_CHANGED);
        engineId = rule.getId();
        state = rule.getEnable();
    }
}
