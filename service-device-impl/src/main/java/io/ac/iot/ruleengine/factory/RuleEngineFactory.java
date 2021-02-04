package io.ac.iot.ruleengine.factory;

import common.util.ObjectUtil;
import io.ac.iot.ruleengine.entity.expression.Expression;
import io.ac.iot.ruleengine.entity.RuleEngine;
import io.ac.iot.ruleengine.model.Scope;
import io.ac.iot.ruleengine.model.TimeRange;
import io.ac.iot.adapter.rest.command.RuleCreateCommand;
import org.springframework.stereotype.Component;

import static common.util.UUIDGenerator.gen32;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-17
 **/
@Component
public class RuleEngineFactory {

    public RuleEngine create(RuleCreateCommand command){

        String id = gen32();
        RuleEngine rule = RuleEngine.builder()
                .id(id)
                .enable(false)
                .name(command.getName())
                .scope(createScope(command))
                .timeRange(createTimeRange(command))
                .expression(buildExpression(command.getExpression()))
                .build();
        rule.postConstruct();
        return rule;
    }

    private Expression buildExpression(String expression) {
        // todo
        return null;
    }

    private Scope createScope(RuleCreateCommand command){

        Scope scope = new Scope();
        scope.setDeviceModelId(command.getDeviceModelId());
        scope.setDeviceIds(command.getDeviceIds());
        scope.setDeviceGroupId(command.getDeviceGroupId());
        return scope;
    }

    private TimeRange createTimeRange(RuleCreateCommand command){

        return ObjectUtil.copyField(command,new TimeRange());
    }
}
