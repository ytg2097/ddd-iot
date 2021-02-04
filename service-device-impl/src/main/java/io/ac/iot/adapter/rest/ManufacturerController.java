package io.ac.iot.adapter.rest;

import common.rest.RestResult;
import io.ac.iot.adapter.rest.command.ManufacturerCreateCommand;
import io.ac.iot.manufacturer.ManufacturerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-02
 **/
@RestController
@RequestMapping("manufacturer")
@Api("设备厂家API")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @ApiOperation(value = "创建设备厂家", notes = "创建设备厂家")
    @PostMapping("/")
    public RestResult<String> create(@RequestBody @Valid ManufacturerCreateCommand createCommand){

        return new RestResult<>(manufacturerService.create(createCommand));
    }


}
