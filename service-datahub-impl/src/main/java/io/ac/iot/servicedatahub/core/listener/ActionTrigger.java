package io.ac.iot.servicedatahub.core.listener;

import io.ac.iot.servicedatahub.aggregation.action.ActionService;
import io.ac.iot.servicedatahub.aggregation.action.root.Action;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.actionrule.ActionChannel;
import io.ac.iot.servicedatahub.core.Listener;
import io.ac.iot.servicedatahub.core.Subject;
import io.ac.iot.servicedatahub.core.bean.MessageContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

import static java.util.stream.Collectors.groupingBy;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-20
 **/
@Component
@RequiredArgsConstructor
public class ActionTrigger extends Subject<ActionChannel,Action> implements Listener<MessageContext> {

    private final ActionService actionService;

    @PostConstruct
    public void addListener(){

        add(ActionChannel.NETTY,new NettyActionListener());
        add(ActionChannel.HTTP,new HttpActionListener());
        add(ActionChannel.TELCOM,new TelcomActionListener());
        add(ActionChannel.MQTT,new MqttActionListener());
    }

    @Override
    public void onMessage(MessageContext message) {

        notifyAll(persist(message));
    }

    private List<Action> persist(MessageContext message) {

        return actionService.persist(message.getDeviceId(), message.getData(), message.getEngine().getEngineId());
    }

    private void notifyAll(List<Action> actionList) {

        actionList.stream().collect(groupingBy(Action::getActionChannel)).forEach((channel,actions) -> {

            for (Action action : actions) {

                notifyAll(channel,action);
            }
        });
    }

    @Component
    public class NettyActionListener implements Listener<Action>{

        @Override
        public void onMessage(Action message) {

        }
    }

    @Component
    public class HttpActionListener implements Listener<Action>{

        @Override
        public void onMessage(Action message) {

        }
    }

    @Component
    public class MqttActionListener implements Listener<Action>{

        @Override
        public void onMessage(Action message) {

        }
    }

    @Component
    public class TelcomActionListener implements Listener<Action>{

        @Override
        public void onMessage(Action message) {

        }
    }
}
