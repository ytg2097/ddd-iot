package io.ac.iot.servicedatahub.core.listener;

import com.netflix.discovery.converters.Auto;
import io.ac.iot.servicedatahub.aggregation.forward.ForwardService;
import io.ac.iot.servicedatahub.aggregation.forward.root.Forward;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.forwardrule.ForwardTargetType;
import io.ac.iot.servicedatahub.core.Dispatcher;
import io.ac.iot.servicedatahub.core.Listener;
import io.ac.iot.servicedatahub.core.Subject;
import io.ac.iot.servicedatahub.core.bean.MessageContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-20
 **/
@Component
@RequiredArgsConstructor
public class ForwardTrigger extends Subject<ForwardTargetType, Forward> implements Listener<MessageContext> {

    private final ForwardService forwardService;

    @PostConstruct
    public void addListener(){
        add(ForwardTargetType.HTTPS,new HttpForwarder());
        add(ForwardTargetType.MQTT,new MqttForwarder());
        add(ForwardTargetType.KAFKA,new KafkaForwarder());
    }


    @Override
    public void onMessage(MessageContext message) {

        notify(persist(message));
    }

    private List<Forward> persist(MessageContext message) {
        return forwardService.persist(message.getDeviceId(), message.getData(), message.getEngine().getEngineId());
    }

    private void notify(List<Forward> persist) {

        for (Forward forward : persist) {

            notifyAll(forward.getForwardTargetType(),forward);
        }
    }

    public abstract class Forwarder implements Listener<Forward>{

        @Override
        public void onMessage(Forward message) {

            dispatcher().dispatch(message, forwardService::postDispatch);
        }

        protected abstract Dispatcher<Forward> dispatcher();
    }

    @Component
    public class HttpForwarder extends Forwarder{

        @Autowired
        @Qualifier("httpDispatcher")
        private Dispatcher<Forward> dispatcher;

        @Override
        protected Dispatcher<Forward> dispatcher() {
            return dispatcher;
        }
    }

    @Component
    public class MqttForwarder extends Forwarder{

        @Autowired
        @Qualifier("mqttDispatcher")
        private Dispatcher<Forward> dispatcher;

        @Override
        protected Dispatcher<Forward> dispatcher() {
            return dispatcher;
        }
    }

    @Component
    public class KafkaForwarder extends Forwarder{

        @Autowired
        @Qualifier("httpDispatcher")
        private Dispatcher<Forward> dispatcher;

        @Override
        protected Dispatcher<Forward> dispatcher() {
            return dispatcher;
        }
    }

}
