package io.ac.iot.servicedatahub.core.dispatcher;

import io.ac.iot.servicedatahub.aggregation.forward.root.Forward;
import io.ac.iot.servicedatahub.core.Dispatcher;
import io.ac.iot.servicedatahub.core.bean.DispatchResultContext;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-20
 **/
@Component
public class MqttDispatcher implements Dispatcher<Forward> {
    @Override
    public void dispatch(Forward val, Consumer<DispatchResultContext<Forward>> callback) {

        //TODO
    }

}
