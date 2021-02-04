package io.ac.iot.device.model;

import com.alibaba.fastjson.JSONObject;
import common.exception.BusinessException;
import io.ac.iot.adapter.rest.command.DeviceCreateCommand;
import io.ac.iot.common.util.RequestIdHolder;
import io.ac.iot.devicemodel.repositories.DevicePropertyParseRuleRepository;
import io.ac.iot.devicemodel.model.DeviceModel;
import io.ac.iot.devicemodel.model.qrcodepaserule.DevicePropertyParseRule;
import io.ac.iot.devicetag.model.DeviceTag;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

import static common.util.UUIDGenerator.gen32;
import static io.ac.iot.device.exception.DeviceExceptionCode.*;
import static io.ac.iot.device.model.DeviceStatus.NORMAL;
import static io.ac.iot.device.model.DeviceStatus.OFFLINE;
import static java.util.stream.Collectors.toList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/
@Component
public class DeviceFactory {

    private final DevicePropertyParseRuleRepository parseRuleRepository;

    public DeviceFactory(DevicePropertyParseRuleRepository parseRuleRepository) {
        this.parseRuleRepository = parseRuleRepository;
    }

    /**
     * 根据扫码输入创建设备
     * @param input
     * @param model
     * @return
     */
    public Device newInstanceByModel(String input, DeviceModel model) {

        Device entity = new Device();
        entity.setId(gen32());
        entity.setStatus(OFFLINE);
        entity.setModelId(model.getId());
        entity.setTenantId(RequestIdHolder.tid.get());

        setProperties(input, model, entity);

        return entity;
    }

    private void setProperties(String input, DeviceModel model, Device entity) {

        JSONObject properties = new JSONObject();
        try {
            rules(model.getId())
                    .forEach(rule -> {
                        properties.put(rule.getPrimaryKey().getRuleKey(), input.substring(rule.getStartPoint(), rule.getStep()));
                        if (rule.isIdentifier()) {
                            entity.setSerial(properties.get(rule.getPrimaryKey().getRuleKey()).toString());
                        }
                    });
//          TODO   entity.setProperties(properties);
        } catch (Exception e) {
            throw new BusinessException(PROPERTY_PARSE_FAIL);
        }
    }

    /**
     * 根据表单提交项创建设备
     * @param createCommand
     * @return
     */
    public Device createByCommand(DeviceCreateCommand createCommand) {

        Device entity = new Device();
        entity.setId(gen32());
        entity.setName(createCommand.getName());
        entity.setStatus(NORMAL);
        entity.setSerial(createCommand.getSerial());
        entity.setUniqueCode(createCommand.getUniqueCode());
        entity.setModelId(createCommand.getModelId());
        entity.setTenantId(RequestIdHolder.tid.get());
        entity.setDeviceGroupId(createCommand.getGroupId());
        entity.setTags(createCommand.getDeviceTagId().stream().map(DeviceTag::new).collect(toList()));

        entity.postConstruct();

        return entity;
    }

    @Cacheable(value = "device-property-parse-rules", key = "#modelId")
    public List<DevicePropertyParseRule> rules(String modelId) {

        List<DevicePropertyParseRule> rules = parseRuleRepository.findByModelId(modelId);
        if (rules.isEmpty()) {
            throw new BusinessException(DEVICE_PROPERTY_PARSE_RULE_NOT_CONFIGURED);
        }
        if (rules.stream().noneMatch(DevicePropertyParseRule::isIdentifier)){
            throw new BusinessException(IDENTIFIER_PARSE_RULE_NOT_CONFIGURED);
        }
        return rules;
    }
}
