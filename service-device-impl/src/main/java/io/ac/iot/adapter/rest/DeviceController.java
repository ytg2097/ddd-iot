package io.ac.iot.adapter.rest;

import common.rest.PageableRestResult;
import common.rest.RestResult;
import io.ac.iot.adapter.rest.representation.DeviceListRepresentation;
import io.ac.iot.adapter.rest.representation.common.IdWithNameRepresentation;
import io.ac.iot.adapter.rest.request.DeviceListRequest;
import io.ac.iot.device.DeviceService;
import io.ac.iot.adapter.rest.command.DeviceCreateCommand;
import io.ac.iot.device.model.Device;
import io.ac.iot.device.represetation.DeviceRepresentation;
import io.ac.starter.util.RequestHeadHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static io.ac.iot.common.util.PageUtil.map;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/
@RestController
@RequestMapping("device")
@Api("device api")
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @ApiOperation(value = "注册设备", notes = "根据id查询")
    @PostMapping
    public RestResult<String> create(@RequestBody @Valid DeviceCreateCommand createCommand){

        String deviceId = deviceService.create(createCommand);
        return new RestResult<>(deviceId);
    }

    @ApiOperation(value = "查询设备", notes = "根据id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",  value = "设备id", required = true, dataType = "String")
    })
    @GetMapping("/{id}")
    public RestResult<DeviceRepresentation> findOne(@PathVariable("id") String id){

        return new RestResult<>(deviceService.findOne(id));
    }

    @ApiOperation(value = "分页")
    @GetMapping("list")
    public PageableRestResult<DeviceListRepresentation> list(DeviceListRequest request){

        return map(deviceService.list(request), Device::toListRepre);
    }

}
