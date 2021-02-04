package io.ac.iot.servicedatahub.aggregation.alarm;

import com.fasterxml.jackson.databind.JsonNode;
import io.ac.iot.servicedatahub.aggregation.alarm.factory.AlarmFactory;
import io.ac.iot.servicedatahub.aggregation.alarm.root.Alarm;
import io.ac.iot.servicedatahub.aggregation.ruleengine.RuleEngineService;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.alarmrule.AlarmRule;
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
 * @create: 2020-11-19
 **/
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class AlarmService {

    private final AlarmFactory alarmFactory;
    private final AlarmRepository alarmRepository;
    private final RuleEngineService ruleEngineService;

    public List<Alarm> persist(String deviceId, JsonNode data, String engineId){

        return ruleEngineService.findAlarmRuleByEngineId(engineId).stream()
                .map(rule -> persist(deviceId, data, rule))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public Alarm persist(String deviceId, JsonNode data, AlarmRule alarmRule){

        try {

            Alarm alarm = alarmFactory.create(deviceId, data, alarmRule);
            alarmRepository.save(alarm);
            return alarm;
        }catch (Exception e){
            log.warn("Alarm persist failed , DeviceId: {}, ReceivedData: {} ,AlarmRuleId: {}",deviceId,data.toString(),alarmRule.getId());
            log.warn(e.getMessage());
            return null;
        }
    }


}
