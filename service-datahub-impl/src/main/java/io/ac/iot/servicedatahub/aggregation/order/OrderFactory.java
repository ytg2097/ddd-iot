package io.ac.iot.servicedatahub.aggregation.order;

import com.fasterxml.jackson.databind.JsonNode;
import io.ac.iot.servicedatahub.aggregation.device.DeviceService;
import io.ac.iot.servicedatahub.aggregation.device.vo.DeviceWithRuleEngineVO;
import io.ac.iot.servicedatahub.aggregation.order.root.Order;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.orderrule.OrderRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-24
 **/
@Component
@RequiredArgsConstructor
public class OrderFactory {

    private final DeviceService deviceService;

    public Order create(String deviceId, JsonNode data, OrderRule orderRule){

        DeviceWithRuleEngineVO device = deviceService.findById(deviceId);
        return Order.builder()
                .content(buildContent(data,orderRule))
                .deviceId(device.getDeviceId())
                .deviceModelId(device.getDeviceModelId())
                .tenantId(device.getTenantId())
                .build();
    }

    private String buildContent(JsonNode data, OrderRule orderRule) {

        Map<String, String> keyAndValue = orderRule.getPlaceholder().stream()
                .collect(toMap(identity(), placeholder ->
                        data.get(placeholder).toString()));
        String content = orderRule.getTemplate();
        for (Map.Entry<String, String> matter : keyAndValue.entrySet()) {
            content = content.replace("#".concat(matter.getKey()),matter.getValue());
        }
        return content;
    }
}
