package io.ac.iot.servicedatahub.adapter.msg;

import io.ac.iot.servicedatahub.adapter.msg.event.device.DeviceCreatedEvent;
import io.ac.iot.servicedatahub.adapter.msg.event.device.DeviceDeletedEvent;
import io.ac.iot.servicedatahub.adapter.msg.event.device.DeviceGroupChangedEvent;
import io.ac.iot.servicedatahub.aggregation.device.DeviceEventHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

import static io.ac.iot.servicedatahub.adapter.msg.DeviceEventListener.DeviceEventTypeWithChannel.*;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-16
 **/
@Component
@RequiredArgsConstructor
@EnableBinding(DeviceEventListener.DeviceEventTypeWithChannel.class)
public class DeviceEventListener {

    private final DeviceEventHandler deviceService;

    @StreamListener(target = DEVICE_GROUP_CHANGED)
    public void onDeviceGroupChanged(DeviceGroupChangedEvent event){

        deviceService.modifyGroup(event);
    }

    @StreamListener(target = DEVICE_CREATED)
    public void onDeviceCreated(DeviceCreatedEvent event){

        deviceService.createDevice(event);
    }

    @StreamListener(target = DEVICE_DELETED)
    public void onDeviceDeleted(DeviceDeletedEvent event){

        deviceService.deleteDevice(event);
    }

    public interface DeviceEventTypeWithChannel {

        String DEVICE_GROUP_CHANGED = "DEVICE_GROUP_CHANGED";
        String DEVICE_CREATED = "DEVICE_CREATED";
        String DEVICE_DELETED = "DEVICE_DELETED";

        /**
         * 设备分组修改
         *
         * @return
         */
        @Input(DEVICE_GROUP_CHANGED)
        SubscribableChannel changed();

        /**
         * 新增设备
         *
         * @return
         */
        @Input(DEVICE_CREATED)
        SubscribableChannel created();

        /**
         * 删除设备
         *
         * @return
         */
        @Input(DEVICE_DELETED)
        SubscribableChannel deleted();
    }
}
