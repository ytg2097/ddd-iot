package io.ac.iot.servicedatahub.adapter.msg.event.rule;

import io.ac.starter.DomainEvent;
import lombok.Getter;

import java.util.Date;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-17
 **/
@Getter
public abstract class BaseRuleEvent extends DomainEvent {

    private final String engineId;


    public BaseRuleEvent(String _id, String _type, Date _createdAt, String engineId) {
        super(_id, _type, _createdAt);
        this.engineId = engineId;
    }

}
