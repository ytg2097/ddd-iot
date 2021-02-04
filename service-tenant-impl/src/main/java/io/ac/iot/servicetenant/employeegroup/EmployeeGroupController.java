package io.ac.iot.servicetenant.employeegroup;

import common.rest.RestResult;
import io.ac.iot.servicetenant.employeegroup.command.EmployeeGroupCreateCommand;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-13
 **/
@RestController
@RequestMapping("/employee-group")
public class EmployeeGroupController {

    private final EmployeeGroupService employeeGroupService;

    public EmployeeGroupController(EmployeeGroupService employeeGroupService) {
        this.employeeGroupService = employeeGroupService;
    }


    @ApiOperation(value = "创建员工群组", notes = "创建员工群组")
    @PostMapping("/")
    public RestResult<String> createGroup(@RequestBody @Valid EmployeeGroupCreateCommand createCommand) {

        String groupId = employeeGroupService.create(createCommand);
        return new RestResult<>(groupId);
    }

    @ApiOperation(value = "移除员工群组", notes = "移除员工群组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "要删除的群组id", required = true, dataType = "String"),
    })
    @DeleteMapping("/")
    public RestResult<Boolean> removeGroup(@RequestParam("groupId") String groupId){

        return new RestResult<>(employeeGroupService.removeGroup(groupId));
    }

    @ApiOperation(value = "向员工群组中添加成员", notes = "向员工群组中添加成员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "当前操作群组id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "employeeId", value = "员工id", required = true, dataType = "Long")
    })
    @PostMapping("/members")
    public RestResult<Boolean> addEmployee(@RequestParam("groupId") String groupId,
                                           @RequestParam("employeeId") Long employeeId){

        return new RestResult<>(employeeGroupService.addEmployeeToGroup(groupId,employeeId));
    }

    @ApiOperation(value = "从员工群组中移除成员", notes = "从员工群组中移除成员")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "员工群组id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "employeeId", value = "员工id", required = true, dataType = "Long")
    })
    @DeleteMapping("/members")
    public RestResult<Boolean> removeEmployee(@RequestParam("groupId") String groupId,
                                           @RequestParam("employeeId") Long employeeId){

        return new RestResult<>(employeeGroupService.removeEmployeeFromGroup(groupId,employeeId));
    }

    @ApiOperation(value = "移动群组", notes = "移动群组")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupId", value = "当前操作群组id", required = true, dataType = "String"),
            @ApiImplicitParam(name = "targetGroupId", value = "移动目标群组id", required = true, dataType = "String")
    })
    @PutMapping("/move")
    public RestResult<Boolean> moveGroup(@RequestParam("groupId") String groupId,
                                              @RequestParam("targetGroupId") String targetGroupId){

        return new RestResult<>(employeeGroupService.moveGroup(groupId,targetGroupId));
    }
}
