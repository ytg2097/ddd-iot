package io.ac.iot.servicedatahub.core.listener;

import io.ac.iot.servicedatahub.aggregation.order.OrderService;
import io.ac.iot.servicedatahub.core.Listener;
import io.ac.iot.servicedatahub.core.bean.MessageContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-24
 **/
@Component
@RequiredArgsConstructor
public class OrderTrigger implements Listener<MessageContext> {

    private final OrderService orderService;

    @Override
    public void onMessage(MessageContext message) {

        orderService.dispatch(message.getDeviceId(),message.getData(),message.getEngine().getEngineId());
    }
}
