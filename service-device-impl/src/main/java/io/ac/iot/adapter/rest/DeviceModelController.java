package io.ac.iot.adapter.rest;

import common.rest.RestResult;
import io.ac.iot.adapter.rest.command.*;
import io.ac.iot.devicemodel.DeviceModelService;
import io.ac.starter.util.RequestHeadHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-01
 **/
@RestController
@RequestMapping("/device-model")
@Api("设备模型API")
public class DeviceModelController {

    private final DeviceModelService deviceModelService;

    public DeviceModelController(DeviceModelService deviceModelService) {
        this.deviceModelService = deviceModelService;
    }

    @ApiOperation(value = "创建设备模型", notes = "创建设备模型")
    @PostMapping
    public RestResult<String> create(@RequestBody @Valid DeviceModelCreateCommand createCommand){

        return new RestResult<>(deviceModelService.create(createCommand));
    }

//    @ApiOperation(value = "创建设备扫码解析规则", notes = "创建设备扫码解析规则")
//    @PostMapping("/{modelId}/property-parse-rule")
//    public RestResult<String> addPropertyParseRule(@PathVariable("modelId")String modelId,
//                                            @RequestBody @Valid List<DevicePropertyParseRuleCreateCommand> createCommand){
//        deviceModelService.addPropertyParseRule(modelId,createCommand);
//        return new RestResult<>();
//    }

    @ApiOperation(value = "新增设备能力", notes = "新增设备能力")
    @PostMapping("/{modelId}/capability")
    public RestResult<String> addCapability(@PathVariable("modelId")String modelId,
                                            DeviceCapabilityCreateCommand createCommand){

        deviceModelService.addCapability(modelId,createCommand);
        return new RestResult<>();
    }

    @ApiOperation(value = "修改设备能力描述", notes = "修改设备能力描述")
    @PutMapping("/capability/info")
    public RestResult<String> modifyCapabilityInfo(@RequestBody @Valid DeviceCapabilityInfoModifyCommand command){

        deviceModelService.modifyCapabilityInfo(command);
        return new RestResult<>();
    }

    @ApiOperation(value = "修改设备能力可用命令列表", notes = "修改设备能力可用命令列表")
    @PutMapping("/capability/command")
    public RestResult<String> modifyCapabilityCommand(@RequestBody @Valid DeviceCapabilityCommandModifyCommand command){

        deviceModelService.modifyCapabilityCommandList(command);
        return new RestResult<>();
    }

    @ApiOperation(value = "修改设备能力属性列表", notes = "修改设备能力属性列表")
    @PutMapping("/capability/properties")
    public RestResult<String> modifyCapabilityPropertyCommand(@RequestBody @Valid DeviceCapabilityPropertyModifyCommand command){

        deviceModelService.modifyCapabilityProperties(command);
        return new RestResult<>();
    }

    @ApiOperation(value = "删除产品模型", notes = "删除产品模型")
    @DeleteMapping
    public RestResult remove(@RequestParam("modelId")String modelId){

        deviceModelService.remove(modelId);
        return new RestResult();
    }

    @ApiOperation(value = "删除产品模型", notes = "删除产品模型")
    @GetMapping
    public RestResult all(){

        return new RestResult(deviceModelService.listByTenantId(RequestHeadHolder.get(RequestHeadHolder.RequestHead.TENANT_ID)));
    }
}
