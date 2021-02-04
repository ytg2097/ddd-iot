package io.ac.iot.servicetenant.employee;

import common.rest.PageableRestResult;
import common.rest.RestResult;
import io.ac.iot.servicelog.OperationLog;
import io.ac.iot.servicetenant.employee.command.EmployeeCreateCommand;
import io.ac.iot.servicetenant.employee.command.EmployeeModifyCommand;
import io.ac.iot.servicetenant.employee.model.Employee;
import io.ac.iot.servicetenant.employee.request.EmployeeListRequestParam;
import io.ac.iot.servicetenant.representation.EmployeeRepresentation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static io.ac.iot.servicelog.OperationLog.Target.Origin.CUSTOMIZE;
import static io.ac.iot.servicelog.OperationLog.Target.Origin.METHOD_ARGS;
import static io.ac.iot.servicelog.OperationLog.Type.OPERATE;
import static io.ac.iot.servicelog.OperationLog.Type.QUERY;
import static java.util.stream.Collectors.toList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-13
 **/
@RestController
@RequestMapping("/employee")
public class EmployeeController{

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @ApiOperation(value = "创建员工", notes = "创建员工")
    @PostMapping
    @OperationLog(type = OPERATE,target = @OperationLog.Target(origin = METHOD_ARGS,name="createCommand.name"),operating = "新建员工")
    public RestResult<Long> create(@RequestBody @Valid EmployeeCreateCommand createCommand){

        return new RestResult<>(employeeService.create(createCommand));
    }

    @ApiOperation(value = "修改员工", notes = "修改员工")
    @PutMapping
    @OperationLog(type = OPERATE,target = @OperationLog.Target(origin = METHOD_ARGS,name="modifyCommand.id"),operating = "修改员工")
    public RestResult modify(@RequestBody @Valid EmployeeModifyCommand modifyCommand){

        employeeService.modify(modifyCommand);
        return new RestResult();
    }

    @ApiOperation(value = "删除员工", notes = "删除员工")
    @DeleteMapping
    @OperationLog(type = OPERATE,target = @OperationLog.Target(origin = METHOD_ARGS,name="id"),operating = "删除员工")
    public RestResult delete(@RequestParam("id") Long id){

        employeeService.delete(id);
        return new RestResult();
    }

    @ApiOperation(value = "查询员工", notes = "查询员工")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employeeId", value = "员工id", required = true, dataType = "String")
    })
    @GetMapping("/{employeeId}")
    public RestResult<EmployeeRepresentation> findEmployee(@PathVariable("employeeId") Long employeeId){

        return new RestResult<>(employeeService.findOne(employeeId));
    }

    @ApiOperation(value = "", notes = "查询员工")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "employeeId", value = "员工id", required = true, dataType = "String")
    })
    @GetMapping("list")
    public PageableRestResult<EmployeeRepresentation> listEmployee(EmployeeListRequestParam requestParam){

        Page<Employee> employees = employeeService.listEmployee(requestParam);
        return new PageableRestResult(employees.get().map(Employee::toRepresentation).collect(toList()),employees.getTotalElements(),employees.getTotalPages());
    }
}
