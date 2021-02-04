package io.ac.iot.servicebackedconfig.adapter.rest;

import common.rest.PageableRestResult;
import common.rest.RestResult;
import io.ac.iot.servicebackedconfig.adapter.rest.command.SetMealCreateCommand;
import io.ac.iot.servicebackedconfig.adapter.rest.representation.SetMealListRepresentation;
import io.ac.iot.servicebackedconfig.adapter.rest.request.SetMealListRequest;
import io.ac.iot.servicebackedconfig.aggregation.setmeal.SetMealService;
import io.ac.iot.servicebackedconfig.aggregation.setmeal.model.AccessibleMenu;
import io.ac.iot.servicebackedconfig.aggregation.setmeal.model.SetMeal;
import io.ac.iot.servicebackedconfig.client.SetMealClient;
import io.ac.iot.servicebackedconfig.dto.MenuDTO;
import io.ac.iot.servicebackedconfig.dto.SetMealDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-23
 **/
@RestController
@RequestMapping("set-meal")
@Api(tags = "套餐")
public class SetMealController implements SetMealClient {

    private final SetMealService setMealService;

    public SetMealController(SetMealService setMealService) {
        this.setMealService = setMealService;
    }

    @ApiOperation("新增套餐")
    @PostMapping
    public RestResult add(@RequestBody @Valid SetMealCreateCommand createCommand){

        setMealService.create(createCommand);
        return new RestResult();
    }

    @ApiOperation("切换套餐启用状态")
    @PutMapping("enable")
    public RestResult toggleEnableStatus(@RequestParam("id")String id){

        setMealService.toggleEnableStatus(id);
        return new RestResult();
    }

    @ApiOperation("可销售的套餐")
    @GetMapping("enabled")
    public RestResult enabledSetMeal(){

        return new RestResult(setMealService.enabledSetMeal());
    }

    @ApiOperation("分页")
    @GetMapping("list")
    public PageableRestResult<SetMealListRepresentation> list(SetMealListRequest request){

        Page<SetMeal> page = setMealService.listSetMeal(request);

        return new PageableRestResult(page.get().map(SetMealListRepresentation::of).collect(toList()),
                page.getTotalElements(),
                page.getTotalPages());
    }

    @Override
    @ApiOperation("套餐详情")
    @GetMapping
    public RestResult<SetMealDTO> detail(String id) {

        SetMeal one = setMealService.getOne(id);
        SetMealDTO dto = new SetMealDTO();
        dto.setAccountNumber(one.getAccountNumber());
        dto.setDeviceNumber(one.getDeviceNumber());
        dto.setName(dto.getName());
        return new RestResult<>(dto);
    }

    @Override
    @ApiOperation("套餐下可见的菜单列表")
    @GetMapping("menus")
    public RestResult<List<MenuDTO>> visibleMenuList(@RequestParam("id") String id) {

        return new RestResult<>(setMealService.findVisibleMenuListById(id));
    }


}
