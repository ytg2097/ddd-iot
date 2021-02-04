package io.ac.iot.servicetenant.client;

import common.rest.PageableRestResult;
import common.rest.RestResult;
import io.ac.iot.servicetenant.command.TenantSetUpSetMealCommand;
import io.ac.iot.servicetenant.representation.TenantRepresentation;
import io.ac.iot.servicetenant.request.TenantListRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-26
 **/
@FeignClient(value = "service-tenant",path = "/tenant")
public interface TenantClient {

    /**
     * find tenant by id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    RestResult<TenantRepresentation> findOne(@PathVariable("id")String id);

    /**
     * 验证租户是否有效
     * @param tenantId
     * @return
     */
    @GetMapping("/valid")
    RestResult<Boolean> valid(@RequestParam("tenantId") String tenantId);

    /**
     * 分页
     * @param request
     * @return
     */
    @GetMapping("list")
    PageableRestResult list(TenantListRequest request);

    /**
     * 设置套餐
     * @param command
     * @return
     */
    @PostMapping("set-meal")
    RestResult setUpSetMeal(@RequestBody @Valid TenantSetUpSetMealCommand command);

}
