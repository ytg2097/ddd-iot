package io.ac.iot.servicedatahub.aggregation.forward;

import com.fasterxml.jackson.databind.JsonNode;
import io.ac.iot.servicedatahub.aggregation.action.root.Action;
import io.ac.iot.servicedatahub.aggregation.forward.factory.ForwardFactory;
import io.ac.iot.servicedatahub.aggregation.forward.root.Forward;
import io.ac.iot.servicedatahub.aggregation.ruleengine.RuleEngineService;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.forwardrule.ForwardRule;
import io.ac.iot.servicedatahub.core.bean.DispatchResultContext;
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
public class ForwardService {

    private final RuleEngineService ruleEngineService;
    private final ForwardFactory forwardFactory;
    private final ForwardRepository forwardRepository;

    public List<Forward> persist(String deviceId, JsonNode data,String engineId){

        return ruleEngineService.findForwardRuleByEngineId(engineId)
                .stream()
                .map(rule -> persist(deviceId, data, rule))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private Forward persist(String deviceId, JsonNode data, ForwardRule rule){

        try {

            Forward forward = forwardFactory.create(deviceId, data,rule);
            forwardRepository.save(forward);
            return forward;
        }catch (Exception e){
            log.warn("Forward persist failed , DeviceId: {}, ReceivedData: {} ,ForwardRuleId: {}",deviceId,data.toString(),rule.getId());
            log.warn(e.getMessage());
            return null;
        }
    }

    public void postDispatch(DispatchResultContext<Forward> context) {

        Forward forward = context.getForward();
        forward.setState(context.getState());
        forwardRepository.save(forward);
    }

}
