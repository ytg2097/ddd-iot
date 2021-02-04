package io.ac.iot.servicebackedconfig.client;

import common.rest.RestResult;
import io.ac.iot.servicebackedconfig.dto.MenuDTO;
import io.ac.iot.servicebackedconfig.dto.SetMealDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-23
 **/
@FeignClient(name = "service-backed-config",path = "/set-meal")
public interface SetMealClient {

    /**
     * 套餐详情
     * @param id
     * @return
     */
    @GetMapping
    RestResult<SetMealDTO> detail(@RequestParam("id") String id);

    /**
     * 可见菜单
     * @param id
     * @return
     */
    @GetMapping("menus")
    RestResult<List<MenuDTO>> visibleMenuList(@RequestParam("id") String id);
}
