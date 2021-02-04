package io.ac.iot.servicedatahub.aggregation.devicemodel;

import io.ac.iot.servicedatahub.adapter.msg.event.devicemodel.DeviceCommandModifiedEvent;
import io.ac.iot.servicedatahub.adapter.msg.event.devicemodel.DevicePropertyModifiedEvent;
import io.ac.iot.servicedatahub.aggregation.devicemodel.root.DeviceModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-24
 **/
@Component
@Transactional(rollbackFor = Exception.class)
@Slf4j
@RequiredArgsConstructor
public class DeviceModelEventHandler {

    private final DeviceModelRepository deviceModelRepository;

    public void on(DeviceCommandModifiedEvent event) {

        Optional<DeviceModel> model = deviceModelRepository.findById(event.getModelId());

        DeviceModel deviceModel = model.orElse(new DeviceModel(event.getModelId()));

        try {
            deviceModel.modifyCommand(event);
        } catch (IOException e) {
            log.error("Device model command modify error ,id: {}, commandList: {}",event.getModelId(),event.getCommandList());
        }

        deviceModelRepository.save(deviceModel);
    }

    public void on(DevicePropertyModifiedEvent event) {

        Optional<DeviceModel> model = deviceModelRepository.findById(event.getModelId());

        DeviceModel deviceModel = model.orElse(new DeviceModel(event.getModelId()));

        try {
            deviceModel.modifyProperties(event);
        } catch (IOException e) {
            log.error("Device model properties modify error ,id: {}, properties: {}",event.getModelId(),event.getProperties());
        }

        deviceModelRepository.save(deviceModel);
    }
}
