package io.ac.iot.adapter.message;

import io.ac.iot.adapter.message.domainevent.EmployeeGroupDeletedEvent;
import io.ac.iot.devicegroup.DeviceGroupService;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

import static io.ac.iot.adapter.message.EmployeeGroupEventHandler.EmployeeGroupEventTypeWithChannel.EMPLOYEEGROUP_DELETED;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-02
 **/
@Component
public class EmployeeGroupEventHandler {

    private final DeviceGroupService deviceGroupService;

    public EmployeeGroupEventHandler(DeviceGroupService deviceGroupService) {
        this.deviceGroupService = deviceGroupService;
    }

    @StreamListener(target = EMPLOYEEGROUP_DELETED)
    public void on(EmployeeGroupDeletedEvent event) {

        deviceGroupService.unbindEmployeeGroup(event.getEmployeeGroupId());
    }

    public interface EmployeeGroupEventTypeWithChannel {

        String EMPLOYEEGROUP_DELETED = "EMPLOYEEGROUP_DELETED";

        /**
         * 租户删除事件信道
         *
         * @return
         */
        @Input(EMPLOYEEGROUP_DELETED)
        SubscribableChannel deleted();
    }
}


