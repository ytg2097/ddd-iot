package io.ac.iot.ruleengine.event;

import io.ac.iot.ruleengine.entity.RuleEngine;
import io.ac.starter.DomainEvent;

import static io.ac.iot.ruleengine.event.RuleEngineEngineEventEnum.RULE_DELETED;


/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-23
 **/
public class RuleEngineDeletedEvent extends DomainEvent {

    public RuleEngineDeletedEvent(RuleEngine rule) {
        super(RULE_DELETED);
    }
}
