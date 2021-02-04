package io.ac.iot.servicedatahub.aggregation.order;

import com.fasterxml.jackson.databind.JsonNode;
import io.ac.iot.servicedatahub.aggregation.device.DeviceService;
import io.ac.iot.servicedatahub.aggregation.device.vo.DeviceWithRuleEngineVO;
import io.ac.iot.servicedatahub.aggregation.order.root.Order;
import io.ac.iot.servicedatahub.aggregation.ruleengine.RuleEngineService;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.orderrule.OrderRule;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-24
 **/
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final RuleEngineService ruleEngineService;
    private final OrderRepository orderRepository;
    private final OrderFactory orderFactory;
//    private final OrderClient


    public void dispatch(String deviceId, JsonNode data,String engineId) {

        OrderRule orderRule = ruleEngineService.findOrderRuleByEngineId(engineId);
        Order order = orderFactory.create(deviceId, data, orderRule);
        orderRepository.save(order);
        // todo 推送到工单服务
    }
}
