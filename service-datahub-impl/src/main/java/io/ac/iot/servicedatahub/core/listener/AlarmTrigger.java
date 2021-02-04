package io.ac.iot.servicedatahub.core.listener;

import io.ac.iot.servicedatahub.aggregation.alarm.AlarmService;
import io.ac.iot.servicedatahub.aggregation.alarm.root.Alarm;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.alarmrule.NotifyType;
import io.ac.iot.servicedatahub.core.Listener;
import io.ac.iot.servicedatahub.core.Subject;
import io.ac.iot.servicedatahub.core.bean.MessageContext;
import io.ac.starter.util.BeanUtil;
import lombok.RequiredArgsConstructor;
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
public class AlarmTrigger extends Subject<NotifyType,Alarm> implements Listener<MessageContext> {

    private final AlarmService alarmService;

    @PostConstruct
    public void addListener(){
//不能 new
        add(NotifyType.EMAIL,new EmailNotifyListener());
        add(NotifyType.MSG,new MsgNotifyListener());
        add(NotifyType.INNER,new InnerNotifyListener());
    }

    @Override
    public void onMessage(MessageContext message) {

        notifyAll(saveAlarm(message));
    }

    /**
     * 通知监听器
     * @param alarmList
     */
    private void notifyAll(List<Alarm> alarmList) {

        for (Alarm alarm : alarmList) {
            notifyAll(alarm.getNotifyType(),alarm);
        }
    }

    /**
     * 保存告警
     * @param message
     * @return
     */
    private List<Alarm> saveAlarm(MessageContext message) {

        return alarmService.persist(message.getDeviceId(), message.getData(), message.getEngine().getEngineId());
    }

    public abstract class AbstractNotifyListener implements Listener<Alarm>{


    }


    @Component
    public class EmailNotifyListener extends AbstractNotifyListener{

        @Override
        public void onMessage(Alarm message) {

        }
    }

    @Component
    public class MsgNotifyListener extends AbstractNotifyListener{

        @Override
        public void onMessage(Alarm message) {

        }
    }

    @Component
    public class InnerNotifyListener extends AbstractNotifyListener{

        @Override
        public void onMessage(Alarm message) {

        }
    }
}
