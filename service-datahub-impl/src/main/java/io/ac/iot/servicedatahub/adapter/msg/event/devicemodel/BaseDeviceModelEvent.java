package io.ac.iot.servicedatahub.adapter.msg.event.devicemodel;

import io.ac.starter.DomainEvent;
import lombok.Getter;

import java.util.Date;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-24
 **/
@Getter
public abstract class BaseDeviceModelEvent extends DomainEvent {

    private final String modelId;

    protected BaseDeviceModelEvent(String _id,
                                   String _type,
                                   Date _createdAt,
                                   String modelId) {
        super(_id, _type, _createdAt);
        this.modelId = modelId;
    }
}
