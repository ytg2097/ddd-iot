package io.ac.iot.servicetenant.tenant;

import common.rest.PageableRestResult;
import common.rest.RestResult;
import io.ac.iot.servicetenant.command.TenantSetUpSetMealCommand;
import io.ac.iot.servicetenant.representation.TenantListRepresentation;
import io.ac.iot.servicetenant.tenant.command.TenantCreateCommand;
import io.ac.iot.servicetenant.client.TenantClient;
import io.ac.iot.servicetenant.representation.TenantRepresentation;
import io.ac.iot.servicetenant.request.TenantListRequest;
import io.ac.iot.servicetenant.tenant.model.Tenant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.util.stream.Collectors.toList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-09
 **/
@RestController
@RequestMapping("/tenant")
@Api("租户API")
public class TenantController implements TenantClient {

    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @ApiOperation(value = "创建租户", notes = "创建租户")
    @PostMapping("/")
    public RestResult<String> createTenant(@RequestBody @Valid TenantCreateCommand createCommand) {

        String tenantId = tenantService.create(createCommand);
        return new RestResult<>(tenantId);
    }

    @ApiOperation(value = "查询租户", notes = "根据id查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "租户id", required = true, dataType = "String")
    })
    @GetMapping("/{id}")
    @Override
    public RestResult<TenantRepresentation> findOne(@PathVariable("id") String id) {

        return new RestResult<>(tenantService.findOne(id));
    }

    @ApiOperation(value = "删除租户", notes = "删除租户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantId", value = "租户id", required = true, dataType = "String")
    })
    @DeleteMapping("/")
    public RestResult delete(@RequestParam("tenantId") String tenantId) {

        tenantService.delete(tenantId);
        return new RestResult();
    }

    @ApiOperation(value = "锁定租户", notes = "锁定租户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "租户id", required = true, dataType = "String")
    })
    @PutMapping("lock")
    public RestResult lock(@RequestParam String id) {

        tenantService.lock(id);
        return new RestResult();
    }

    @ApiOperation(value = "激活租户", notes = "激活租户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "租户id", required = true, dataType = "String")
    })
    @PutMapping("active")
    public RestResult active(@RequestParam String id) {

        tenantService.active(id);
        return new RestResult();
    }

    @ApiOperation(value = "验证租户是否可用", notes = "验证租户是否可用")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantId", value = "租户id", required = true, dataType = "String")
    })
    @GetMapping("/valid")
    @Override
    public RestResult<Boolean> valid(@RequestParam("tenantId") String tenantId){

        return new RestResult<>(tenantService.valid(tenantId));
    }

    @ApiOperation(value = "租户列表")
    @GetMapping("list")
    @Override
    public PageableRestResult list(TenantListRequest request){

        Page<Tenant> page = tenantService.listTenant(request);
        return new PageableRestResult(page.get().map(tenant -> {

            TenantListRepresentation rep = new TenantListRepresentation();
            rep.setId(tenant.getId());
            rep.setName(tenant.getName());
            rep.setEmail(tenant.getEmail());
            rep.setFixedPhone(tenant.getFixedPhone());
            rep.setSetMealId(tenant.getSetMeal().getSetMealId());
            rep.setRootAccount(tenant.getAccount().getLoginName());
            rep.setStatus(tenant.getStatus().name);
            return rep;
        }).collect(toList()),page.getTotalElements(),page.getTotalPages());
    }

    @ApiOperation("设置套餐")
    @PostMapping("set-meal")
    @Override
    public RestResult setUpSetMeal(@RequestBody @Valid TenantSetUpSetMealCommand command){

        tenantService.setUpSetMeal(command);
        return new RestResult();
    }


}
