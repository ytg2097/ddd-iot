package io.ac.iot.servicedatahub.adapter.msg;

import io.ac.iot.servicedatahub.adapter.msg.event.devicemodel.DeviceCommandModifiedEvent;
import io.ac.iot.servicedatahub.adapter.msg.event.devicemodel.DevicePropertyModifiedEvent;
import io.ac.iot.servicedatahub.aggregation.devicemodel.DeviceModelEventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-24
 **/
@Component
@RequiredArgsConstructor
public class DeviceModelEventListener {

    private final DeviceModelEventHandler eventHandler;

    public void onCommandModified(DeviceCommandModifiedEvent event){

        eventHandler.on(event);
    }

    public void onPropertyModified(DevicePropertyModifiedEvent event){

        eventHandler.on(event);
    }

}
