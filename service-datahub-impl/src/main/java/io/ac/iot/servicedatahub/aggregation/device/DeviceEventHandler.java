package io.ac.iot.servicedatahub.aggregation.device;

import io.ac.iot.servicedatahub.adapter.msg.event.device.DeviceCreatedEvent;
import io.ac.iot.servicedatahub.adapter.msg.event.device.DeviceDeletedEvent;
import io.ac.iot.servicedatahub.adapter.msg.event.device.DeviceGroupChangedEvent;
import io.ac.iot.servicedatahub.aggregation.device.root.Device;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-16
 **/
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class DeviceEventHandler {

    private final DeviceRepository deviceRepository;
    private final DeviceService deviceService;

    /**
     * 修改设备信息
     * 同时清除设备缓存
     * @param event
     */
    public void modifyGroup(DeviceGroupChangedEvent event) {

        Device device = deviceRepository.findByDeviceId(event.getDeviceId());

        if (Objects.nonNull(device)){

            device.modifyGroupId(event);
        }
        deviceRepository.save(device);
        deviceService.evictCache(event.getDeviceId());
    }

    public void createDevice(DeviceCreatedEvent event) {

        Device device = Device.builder()
                .modelId(event.getModelId())
                .uniqueCode(event.getUniqueCode())
                .tenantId(event.getTenantId())
                .deviceId(event.getDeviceId())
                .build();
        deviceRepository.save(device);
    }

    /**
     * 删除设备
     * 同时清除设备缓存
     * @param event
     */
    public void deleteDevice(DeviceDeletedEvent event) {

        Device device = Device.builder()
                .deviceId(event.getDeviceId())
                .build();
        deviceRepository.delete(device);
        deviceService.evictCache(event.getDeviceId());
    }
}
