package io.ac.iot.servicedatahub.aggregation.ruleengine.factory;

import common.JsonUtil;
import common.exception.BusinessException;
import common.util.ObjectUtil;
import io.ac.iot.servicedatahub.adapter.msg.event.rule.RuleCreatedEvent;
import io.ac.iot.servicedatahub.aggregation.ruleengine.model.Scope;
import io.ac.iot.servicedatahub.aggregation.ruleengine.model.TimeRange;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.expression.Expression;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.RuleEngine;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Serializable;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-18
 **/
@Component
@Slf4j
public class RuleFactory implements Serializable {

    public RuleEngine create(RuleCreatedEvent event){

        return RuleEngine.builder()
                .engineId(event.getEngineId())
                .scope(createScope(event))
                .timeRange(createTimeRange(event))
                .expression(createExpressions(event))
                .build();
    }

    private Expression createExpressions(RuleCreatedEvent event) {

        try {
            return JsonUtil.fromJson(event.getExpression(),Expression.class);
        } catch (IOException e) {
            log.error("Rule expression deserialization error  {}, ruleId  {}",e.getMessage(),event.getEngineId());
            throw new BusinessException(500,e.getMessage());
        }
    }

    private TimeRange createTimeRange(RuleCreatedEvent event) {

       return ObjectUtil.copyField(event,new TimeRange());
    }

    private Scope createScope(RuleCreatedEvent event) {

        Scope scope = new Scope();
        scope.setDeviceModelId(event.getDeviceModelId());
        scope.setDeviceGroupId(event.getDeviceGroupId());
        scope.setDeviceIds(event.getDeviceIds());
        return scope;
    }
}
