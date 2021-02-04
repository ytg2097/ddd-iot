package io.ac.iot.servicebackedconfig.adapter.rest;

import common.rest.RestResult;
import io.ac.iot.servicebackedconfig.adapter.rest.command.MenuCreateCommand;
import io.ac.iot.servicebackedconfig.adapter.rest.command.MenuModifyCommand;
import io.ac.iot.servicebackedconfig.adapter.rest.representation.MenuRepresentation;
import io.ac.iot.servicebackedconfig.aggregation.menu.ATMenuService;
import io.ac.iot.servicebackedconfig.aggregation.menu.MenuService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-30
 **/
@RestController
@RequestMapping("menu")
@RequiredArgsConstructor
public class MenuController{

    private final MenuService menuService;
    private final ATMenuService atMenuService;


    @ApiOperation(value = "用于角色资源分配", notes = "用于角色资源分配")
    @GetMapping("/simple")
    public RestResult allMenu(){

        return new RestResult(menuService.simpleRepresentation());
    }

    @ApiOperation(value = "新增")
    @PostMapping
    public RestResult create(@RequestBody @Valid MenuCreateCommand createCommand){

        menuService.create(createCommand);
        return new RestResult();
    }

    @ApiOperation(value = "修改")
    @PutMapping
    public RestResult modify(@RequestBody @Valid MenuModifyCommand modifyCommand){

        menuService.modify(modifyCommand);
        return new RestResult();
    }

    @ApiOperation(value = "删除")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String")
    })
    @DeleteMapping
    public RestResult delete(String id){

        atMenuService.delete(id);
        return new RestResult();
    }

    @ApiOperation(value = "所有菜单", notes = "所有菜单")
    @GetMapping
    public RestResult<List<MenuRepresentation>> all(){

        return new RestResult(menuService.all());
    }

}
