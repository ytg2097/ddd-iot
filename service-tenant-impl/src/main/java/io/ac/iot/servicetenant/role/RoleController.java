package io.ac.iot.servicetenant.role;

import common.rest.PageableRestResult;
import common.rest.RestResult;
import io.ac.iot.servicelog.OperationLog;
import io.ac.iot.servicetenant.client.RoleClient;
import io.ac.iot.servicetenant.role.command.RoleCreateCommand;
import io.ac.iot.servicetenant.role.command.RoleModifyCommand;
import io.ac.iot.servicetenant.role.model.Role;
import io.ac.iot.servicetenant.role.representation.RouterRepresentation;
import io.ac.iot.servicetenant.role.request.RoleListRequestParam;
import io.ac.starter.util.RequestHeadHolder;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.ac.iot.servicelog.OperationLog.Target.Origin.CUSTOMIZE;
import static io.ac.iot.servicelog.OperationLog.Target.Origin.METHOD_ARGS;
import static io.ac.iot.servicelog.OperationLog.Type.OPERATE;
import static io.ac.iot.servicelog.OperationLog.Type.QUERY;
import static java.util.stream.Collectors.toList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-12
 **/
@RestController
@RequestMapping("/role")
public class RoleController implements RoleClient {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @ApiOperation(value = "创建角色", notes = "创建角色")
    @PostMapping
    @OperationLog(type = OPERATE,target = @OperationLog.Target(origin = METHOD_ARGS,name="createCommand.name"),operating = "创建角色")
    public RestResult<String> create(@RequestBody RoleCreateCommand createCommand){

        String id = roleService.create(createCommand);
        return new RestResult(id);
    }

    @ApiOperation(value = "修改角色", notes = "修改角色")
    @PutMapping
    @OperationLog(type = OPERATE,target = @OperationLog.Target(origin = METHOD_ARGS,name="modifyCommand.id"),operating = "修改角色")
    public RestResult modify(@RequestBody RoleModifyCommand modifyCommand){

       roleService.modify(modifyCommand);
        return new RestResult();
    }

    @ApiOperation(value = "用于角色分配", notes = "用于角色分配")
    @GetMapping("/simple")
    public RestResult<String> allRole(){

        return new RestResult(roleService.findAll().stream().map(Role::toSimpleRepresentation).collect(toList()));
    }

    @ApiOperation(value = "删除角色", notes = "删除角色")
    @DeleteMapping
    @OperationLog(type = OPERATE,target = @OperationLog.Target(origin = METHOD_ARGS,name="id)"),operating = "删除角色")
    public RestResult delete(@RequestParam("id")String id){

        roleService.delete(id);
        return new RestResult();
    }

//    @ApiOperation(value = "创建基础角色", notes = "创建基础角色")
//    @PostMapping("/root")
//    public RestResult create(@RequestParam("tenantId")String tenantId){
//
//        roleService.createRoot(tenantId);
//        return new RestResult();
//    }


    @ApiOperation(value = "角色详情", notes = "角色详情")
    @GetMapping("/{id}/detail")
    public RestResult detail(@PathVariable("id")String id){

        return new RestResult(roleService.detail(id));
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("list")
    @OperationLog(type = QUERY,target = @OperationLog.Target(origin = CUSTOMIZE,name="账号列表"),operating = "查询")
    public PageableRestResult listRole(RoleListRequestParam param) {

        Page<Role> roles = roleService.listRole(param);
        return new PageableRestResult(roles.get().map(Role::toRepresentation).collect(toList()),roles.getTotalElements(),roles.getTotalPages());
    }

    @Override
    @ApiOperation("清除已删除菜单的路由信息")
    @DeleteMapping("invalid-menu")
    public RestResult clearInvalidMenu(String menuId) {

        roleService.clearInvalidMenu(menuId);
        return new RestResult();
    }

    @ApiOperation("返回用户可见路由表")
    @GetMapping("visible-routes")
    public RestResult<List<RouterRepresentation>> routes(){

        return new RestResult<>(roleService.routes(RequestHeadHolder.get(RequestHeadHolder.RequestHead.OPERATOR_ID)));
    }
}
