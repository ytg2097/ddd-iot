package io.ac.iot.servicedatahub.aggregation.action.facotry;

import com.fasterxml.jackson.databind.JsonNode;
import io.ac.iot.servicedatahub.aggregation.action.root.Action;
import io.ac.iot.servicedatahub.aggregation.device.DeviceRepository;
import io.ac.iot.servicedatahub.aggregation.device.root.Device;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.actionrule.ActionRule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-20
 **/
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true,rollbackFor = Exception.class)
public class ActionFactory {

    private final DeviceRepository deviceRepository;

    public List<Action> create(String deviceId, JsonNode data, ActionRule actionRule) {

        return deviceList(actionRule).stream()
                .map(device ->
                    Action.builder()
                            // todo
//                .actionChannel()
                            .deviceId(deviceId)
                            .deviceModelId(device.getModelId())
                            .tenantId(device.getTenantId())
                            .commandId(actionRule.getCommandId())
                            .command(buildCommand(device,data,actionRule))
                            .build()
                )
                .collect(Collectors.toList());
    }

    private String buildCommand(Device device, JsonNode data, ActionRule actionRule) {
        //todo
        return null;
    }

    private List<Device> deviceList(ActionRule rule){

        return deviceRepository.findDistinctByDeviceIdInOrGroupIdIn(rule.getDeviceIds(),rule.getDeviceGroupId());
    }
}
