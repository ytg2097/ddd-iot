package io.ac.iot.adapter.rest;

import common.rest.RestResult;
import io.ac.iot.adapter.rest.representation.common.IdWithNameRepresentation;
import io.ac.iot.devicegroup.DeviceGroupService;
import io.ac.iot.device.DeviceService;
import io.ac.iot.adapter.rest.command.DeviceGroupCreateCommand;
import io.ac.starter.util.RequestHeadHolder;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-13
 **/
@RestController
@RequestMapping("/device-group")
public class DeviceGroupController {

    private final DeviceGroupService deviceGroupService;
    private final DeviceService deviceService;

    public DeviceGroupController(DeviceGroupService deviceGroupService, DeviceService deviceService) {
        this.deviceGroupService = deviceGroupService;
        this.deviceService = deviceService;
    }


    @ApiOperation(value = "创建设备群组", notes = "创建设备群组")
    @PostMapping
    public RestResult<String> createGroup(@RequestBody @Valid DeviceGroupCreateCommand createCommand) {

        String groupId = deviceGroupService.create(createCommand);
        return new RestResult<>(groupId);
    }

    @ApiOperation(value = "移除设备群组", notes = "移除设备群组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "要删除的群组id", required = true, dataType = "String"),
    })
    @DeleteMapping
    public RestResult<Boolean> removeGroup(@RequestParam("groupId") String groupId){

        return new RestResult<>(deviceGroupService.removeGroup(groupId));
    }


    @ApiOperation(value = "移动群组", notes = "移动群组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "当前操作群组id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "targetGroupId", value = "移动目标群组id", required = true, dataType = "String")
    })
    @PutMapping("/move")
    public RestResult<Boolean> moveGroup(@RequestParam("groupId") String groupId,
                                              @RequestParam("targetGroupId") String targetGroupId){

        return new RestResult<>(deviceGroupService.moveGroup(groupId,targetGroupId));
    }

    @ApiOperation(value = "将设备绑定到群组中", notes = "将设备绑定到群组中")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "当前操作群组id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "deviceIds", value = "设备id", required = true, dataType = "List")
    })
    @PutMapping("/{groupId}/devices/bind")
    public RestResult<Boolean> bindDevice(@PathVariable("groupId") String groupId,
                                           @RequestParam("deviceIds") List<String> deviceIds){

        return new RestResult<>(deviceService.bindGroup(groupId,deviceIds));
    }

    @ApiOperation(value = "从群组中解绑设备", notes = "从群组中解绑设备")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceIds", value = "设备id", required = true, dataType = "List")
    })
    @PutMapping("/devices/unbind")
    public RestResult<Boolean> unbindDevice(@RequestParam("deviceIds") List<String> deviceIds){


        return new RestResult<>(deviceService.unbindGroup(deviceIds));
    }

    @ApiOperation(value = "绑定设备群组与员工群租", notes = "绑定设备群组与员工群租")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "deviceGroupId", value = "设备群组id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "employeeGroupId", value = "员工群组Id", required = true, dataType = "String")
    })
    @PutMapping("/devices")
    public RestResult<Boolean> bindEmployeeGroup(@RequestParam("deviceGroupId") String deviceGroupId,
                                                 @RequestParam("employeeGroupId") String employeeGroupId){

        return new RestResult<>(deviceGroupService.bindEmployeeGroup(deviceGroupId,employeeGroupId));
    }

    @ApiOperation(value = "绑定设备群组与员工群租", notes = "绑定设备群组与员工群租")
    @GetMapping
    public RestResult<List<IdWithNameRepresentation>> all(){

        return new RestResult<>(deviceGroupService.listByTenantId(RequestHeadHolder.get(RequestHeadHolder.RequestHead.TENANT_ID)));
    }
}
