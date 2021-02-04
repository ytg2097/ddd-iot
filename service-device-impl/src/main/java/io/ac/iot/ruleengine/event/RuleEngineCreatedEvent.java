package io.ac.iot.ruleengine.event;

import io.ac.iot.ruleengine.entity.RuleEngine;
import io.ac.starter.DomainEvent;
import lombok.Getter;

import java.util.List;

import static io.ac.iot.ruleengine.event.RuleEngineEngineEventEnum.RULE_CREATED;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-18
 **/
@Getter
public class RuleEngineCreatedEvent extends DomainEvent {

    private String engineId ;

    private  Boolean mon;

    private  Boolean tue;

    private  Boolean wed;

    private  Boolean thu;

    private  Boolean fri;

    private  Boolean sat;

    private  Boolean sun;

    private  String startTime;

    private  String endTime;

    private  String deviceModelId;

    private  String deviceGroupId;

    private  List<String> deviceIds;

    private  String expression;
    
    public RuleEngineCreatedEvent(RuleEngine rule) {
        super(RULE_CREATED);
        engineId = rule.getId();
        mon = rule.getTimeRange().getMon();
        tue = rule.getTimeRange().getTue();
        wed = rule.getTimeRange().getWed();
        thu = rule.getTimeRange().getThu();
        fri = rule.getTimeRange().getFri();
        sat = rule.getTimeRange().getSat();
        sun = rule.getTimeRange().getSun();
        startTime = rule.getTimeRange().getBeginTime();
        endTime = rule.getTimeRange().getEndTime();
        deviceModelId = rule.getScope().getDeviceModelId();
        deviceGroupId = rule.getScope().getDeviceGroupId();
        deviceIds = rule.getScope().getDeviceIds();
        expression = rule.getExpression().toString();
    }

}
