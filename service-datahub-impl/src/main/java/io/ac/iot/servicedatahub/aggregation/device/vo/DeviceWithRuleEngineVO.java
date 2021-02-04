package io.ac.iot.servicedatahub.aggregation.device.vo;

import io.ac.iot.servicedatahub.aggregation.device.root.Device;
import io.ac.iot.servicedatahub.aggregation.ruleengine.root.RuleEngine;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-23
 **/
@Getter
@Setter
public class DeviceWithRuleEngineVO implements Serializable {

    private String deviceId;

    private String tenantId;

    private String deviceModelId;

    private String deviceGroupId;

    private List<RunnableEngine> engines;

    public static DeviceWithRuleEngineVO of(Device device, List<RuleEngine> engines){

        DeviceWithRuleEngineVO result = new DeviceWithRuleEngineVO();
        result.deviceId = device.getDeviceId();
        result.tenantId = device.getTenantId();
        result.deviceGroupId = device.getGroupId();
        result.deviceModelId = device.getModelId();
        result.engines = engines.stream().map(RunnableEngine::new).collect(Collectors.toList());
        return result;
    }


    @Getter
    @Setter
    public static class RunnableEngine implements Serializable{

        private String engineId;

        public RunnableEngine(RuleEngine engine) {
            this.engineId = engine.getEngineId();
        }
    }
}
