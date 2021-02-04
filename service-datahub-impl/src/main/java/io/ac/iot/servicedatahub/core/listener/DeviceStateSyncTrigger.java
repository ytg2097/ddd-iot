package io.ac.iot.servicedatahub.core.listener;

import io.ac.iot.servicedatahub.core.Listener;
import io.ac.iot.servicedatahub.core.bean.MessageContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-20
 **/
@Component
@Slf4j
public class DeviceStateSyncTrigger implements Listener<MessageContext> {

    @Override
    public void onMessage(MessageContext message) {

            //todo
    }
}
