package io.ac.iot.devicemodel;

import common.exception.BusinessException;
import io.ac.iot.adapter.rest.command.*;
import io.ac.iot.adapter.rest.representation.common.IdWithNameRepresentation;
import io.ac.iot.device.DeviceRepository;
import io.ac.iot.devicemodel.model.DeviceModel;
import io.ac.iot.devicemodel.model.capability.DeviceCapability;
import io.ac.iot.devicemodel.model.qrcodepaserule.DevicePropertyParseRule;
import io.ac.iot.devicemodel.model.qrcodepaserule.PropertyParseRulePK;
import io.ac.iot.devicemodel.repositories.DeviceCapabilityRepository;
import io.ac.iot.devicemodel.repositories.DeviceModelRepository;
import io.ac.iot.devicemodel.repositories.DevicePropertyParseRuleRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.ac.iot.device.exception.DeviceExceptionCode.*;
import static java.util.stream.Collectors.toList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-01
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class DeviceModelService {

    private final DeviceModelRepository deviceModelRepository;
    private final DevicePropertyParseRuleRepository ruleRepository;
    private final DeviceRepository deviceRepository;
    private final DeviceCapabilityRepository capabilityRepository;


    public DeviceModelService(DeviceModelRepository deviceModelRepository, DevicePropertyParseRuleRepository ruleRepository, DeviceRepository deviceRepository, DeviceCapabilityRepository capabilityRepository) {
        this.deviceModelRepository = deviceModelRepository;
        this.ruleRepository = ruleRepository;
        this.deviceRepository = deviceRepository;
        this.capabilityRepository = capabilityRepository;
    }

    /**
     * 新增产品模型
     * @param createCommand
     * @return
     */
    public String create(DeviceModelCreateCommand createCommand) {

        if (deviceModelRepository.existsByManufacturerIdAndModel(createCommand.getManufacturerId(),createCommand.getModel())){
            throw new BusinessException(DEVICE_MODEL_ALREADY_EXISTED);
        }

        DeviceModel deviceModel = DeviceModelFactory.create(createCommand);
        deviceModelRepository.save(deviceModel);
        return deviceModel.getId();
    }

//    /**
//     * 添加设备扫码解析规则
//     * @param modelId
//     * @param createCommands
//     */
//    @CacheEvict(value = "device-property-parse-rules",key = "#modelId")
//    public void addPropertyParseRule(String modelId, List<DevicePropertyParseRuleCreateCommand> createCommands) {
//
//        List<DevicePropertyParseRule> existRules = ruleRepository.findAllById(createCommands.stream().map(command -> PropertyParseRulePK.builder().modelId(modelId).ruleKey(command.getKey()).build()).collect(toList()));
//        if (!existRules.isEmpty()){
//            throw new BusinessException(DEVICE_PROPERTY_PARSE_RULE_ALREADY_EXISTED);
//        }
//        List<DevicePropertyParseRule> rules = ruleRepository.findByModelId(modelId);
//        List<DevicePropertyParseRule> newRules = createCommands.stream().map(command -> DevicePropertyParseRule.create(modelId, command)).collect(toList());
//        rules.addAll(newRules);
//        if(rules.stream().filter(DevicePropertyParseRule::isIdentifier).count() > 1){
//            throw new BusinessException(IDENTIFIER_PARSE_RULE_ALREADY_EXISTED);
//        }
//        ruleRepository.saveAll(newRules);
//    }

    /**
     * 移除产品模型
     * @param id
     */
    public void remove(String id){

        Long total = deviceRepository.countByModelIdEquals(id);

        if (total > 0){
            throw new BusinessException(THERE_ARE_DEVICES_BOUND_TO_THIS_MODEL);
        }

        deviceRepository.deleteById(id);
    }

    /**
     * 新增设备能力描述
     * @param modelId
     * @param createCommand
     */
    public void addCapability(String modelId, DeviceCapabilityCreateCommand createCommand) {

        if (capabilityRepository.existsByNameAndModelId(createCommand.getName(),modelId)){
            throw new BusinessException(SERVICE_CAPABILITY_IS_DEFINED);
        }

        DeviceCapability capability = DeviceCapability.create(modelId, createCommand);

        capabilityRepository.save(capability);
    }

    /**
     * 修改设备能力描述
     * @param command
     */
    public void modifyCapabilityInfo(DeviceCapabilityInfoModifyCommand command) {

        DeviceCapability capability = capabilityRepository.getOne(command.getId());

        if (capabilityRepository.existsByNameAndModelId(command.getName(),capability.getModelId())){
            throw new BusinessException(SERVICE_CAPABILITY_IS_DEFINED);
        }

        capability.modifyInfo(command);

        capabilityRepository.save(capability);
    }

    /**
     * 修改设备命令
     * @param command
     */
    public void modifyCapabilityCommandList(DeviceCapabilityCommandModifyCommand command) {

        DeviceCapability capability = capabilityRepository.getOne(command.getCapabilityId());

        capability.modifyCommandList(command);

        capabilityRepository.doSave(capability);
    }

    /**
     * 修改设备属性
     * @param command
     */
    public void modifyCapabilityProperties(DeviceCapabilityPropertyModifyCommand command) {

        DeviceCapability capability = capabilityRepository.getOne(command.getCapabilityId());

        capability.modifyProperties(command);

        capabilityRepository.doSave(capability);
    }


    public List<IdWithNameRepresentation> listByTenantId(String tid) {

        return IdWithNameRepresentation.newInstance(deviceModelRepository.findByTenantIdEquals(tid));
    }
}
