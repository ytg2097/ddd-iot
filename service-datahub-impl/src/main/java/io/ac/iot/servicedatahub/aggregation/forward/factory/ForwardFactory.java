package io.ac.iot.servicedatahub.aggregation.forward.factory;

import com.fasterxml.jackson.databind.JsonNode;
import io.ac.iot.servicedatahub.aggregation.device.DeviceService;
import io.ac.iot.servicedatahub.aggregation.device.root.Device;
import io.ac.iot.servicedatahub.aggregation.device.vo.DeviceWithRuleEngineVO;
import io.ac.iot.servicedatahub.aggregation.forward.root.Forward;
import io.ac.iot.servicedatahub.aggregation.ruleengine.RuleEngineService;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.forwardrule.ForwardRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-20
 **/
@Component
@RequiredArgsConstructor
public class ForwardFactory {

    private final DeviceService deviceService;

    public Forward create(String deviceId, JsonNode data, ForwardRule rule) {

        DeviceWithRuleEngineVO device = device(deviceId);
        return Forward.builder()
                .deviceId(deviceId)
                .deviceModelId(device.getDeviceModelId())
                .tenantId(device.getTenantId())
                .state(false)
                .body(buildBody(data,rule))
                .forwardTargetType(rule.getTargetType())
                .target(rule.getTarget())
                .build();

    }

    private String buildBody(JsonNode data, ForwardRule rule) {

        Map<String, String> keyAndValue = rule.getPlaceholder().stream()
                .collect(toMap(identity(), placeholder ->
                        data.get(placeholder).toString()));
        String content = rule.getContent();
        for (Map.Entry<String, String> matter : keyAndValue.entrySet()) {
            content = content.replace("#".concat(matter.getKey()),matter.getValue());
        }
        return content;
    }

    private DeviceWithRuleEngineVO device(String deviceId) {

        return deviceService.findById(deviceId);
    }
}
