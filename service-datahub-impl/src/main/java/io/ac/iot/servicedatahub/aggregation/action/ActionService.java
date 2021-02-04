package io.ac.iot.servicedatahub.aggregation.action;

import com.fasterxml.jackson.databind.JsonNode;
import io.ac.iot.servicedatahub.aggregation.action.facotry.ActionFactory;
import io.ac.iot.servicedatahub.aggregation.action.root.Action;
import io.ac.iot.servicedatahub.aggregation.ruleengine.RuleEngineService;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.actionrule.ActionRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-20
 **/
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class ActionService {

    private final ActionFactory actionFactory;
    private final ActionRepository actionRepository;
    private final RuleEngineService ruleEngineService;

    public List<Action> persist(String deviceId, JsonNode data, String engineId){

        return ruleEngineService.findActionRuleByEngineId(engineId).stream()
                .map(rule -> persist(deviceId, data, rule))
                .flatMap(List::stream)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<Action> persist(String deviceId, JsonNode data, ActionRule actionRule){

        try {

            List<Action> alarm = actionFactory.create(deviceId, data, actionRule);
            actionRepository.saveAll(alarm);
            return alarm;
        }catch (Exception e){
            log.warn("Action persist failed , DeviceId: {}, ReceivedData: {} ,ActionRuleId: {}",deviceId,data.toString(),actionRule.getId());
            log.warn(e.getMessage());
            return null;
        }
    }
}
