package io.ac.iot.servicedatahub.adapter.msg;

import io.ac.iot.servicedatahub.adapter.msg.event.rule.RuleCreatedEvent;
import io.ac.iot.servicedatahub.adapter.msg.event.rule.RuleModifiedEvent;
import io.ac.iot.servicedatahub.adapter.msg.event.rule.RuleStateChangedEvent;
import io.ac.iot.servicedatahub.aggregation.ruleengine.RuleEventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

import static io.ac.iot.servicedatahub.adapter.msg.RuleEventListener.RuleEventTypeWithChannel.*;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-17
 **/
@Component
@RequiredArgsConstructor
@EnableBinding(RuleEventListener.RuleEventTypeWithChannel.class)
public class RuleEventListener {

    private final RuleEventHandler ruleEventHandler;

    @StreamListener(target = RULE_CREATED)
    public void onRuleEngineCreated(RuleCreatedEvent event){

        ruleEventHandler.on(event);
    }

    @StreamListener(target = RULE_DELETED)
    public void onRuleEngineDeleted(RuleCreatedEvent event){

        ruleEventHandler.on(event);
    }

    @StreamListener(target = RULE_STATE_CHANGED)
    public void onRuleEngineStateChanged(RuleStateChangedEvent event){

        ruleEventHandler.on(event);
    }

    @StreamListener(target = RULE_MODIFIED)
    public void onRuleEngineModified(RuleModifiedEvent event){

        ruleEventHandler.on(event);
    }

    public interface RuleEventTypeWithChannel{

        String RULE_CREATED = "RULE_CREATED";
        String RULE_DELETED = "RULE_DELETED";
        String RULE_MODIFIED = "RULE_MODIFIED";
        String RULE_STATE_CHANGED = "RULE_STATE_CHANGED";

        /**
         * 删除规则
         *
         * @return
         */
        @Input(RULE_DELETED)
        SubscribableChannel deleted();

        /**
         * 新建规则
         *
         * @return
         */
        @Input(RULE_CREATED)
        SubscribableChannel created();

        /**
         * 规则变更
         *
         * @return
         */
        @Input(RULE_MODIFIED)
        SubscribableChannel modified();

        /**
         * 状态改变
         *
         * @return
         */
        @Input(RULE_STATE_CHANGED)
        SubscribableChannel stateChanged();
    }
}
