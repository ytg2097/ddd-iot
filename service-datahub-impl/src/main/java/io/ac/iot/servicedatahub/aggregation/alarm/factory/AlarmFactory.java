package io.ac.iot.servicedatahub.aggregation.alarm.factory;

import com.fasterxml.jackson.databind.JsonNode;
import io.ac.iot.servicedatahub.aggregation.alarm.root.Alarm;
import io.ac.iot.servicedatahub.aggregation.device.DeviceService;
import io.ac.iot.servicedatahub.aggregation.device.root.Device;
import io.ac.iot.servicedatahub.aggregation.device.vo.DeviceWithRuleEngineVO;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.alarmrule.AlarmRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-19
 **/
@Component
@RequiredArgsConstructor
public class AlarmFactory {

    private final DeviceService deviceService;

    public Alarm create(String deviceId, JsonNode data, AlarmRule alarmRule){

        DeviceWithRuleEngineVO device = device(deviceId);
        return Alarm.builder()
                .deviceId(deviceId)
                .level(alarmRule.getLevel())
                .notifyType(alarmRule.getNotifyType())
                .deviceId(device.getDeviceId())
                .deviceModelId(device.getDeviceModelId())
                .content(buildContent(data,alarmRule))
                .build();
    }

    private String buildContent(JsonNode data, AlarmRule alarmRule) {

        Map<String, String> keyAndValue = alarmRule.getPlaceholder().stream()
                .collect(toMap(identity(), placeholder ->
                        data.get(placeholder).toString()));
        String content = alarmRule.getContent();
        for (Map.Entry<String, String> matter : keyAndValue.entrySet()) {
            content = content.replace("#".concat(matter.getKey()),matter.getValue());
        }
        return content;
    }

    private DeviceWithRuleEngineVO device(String deviceId){

        return deviceService.findById(deviceId);
    }
}
