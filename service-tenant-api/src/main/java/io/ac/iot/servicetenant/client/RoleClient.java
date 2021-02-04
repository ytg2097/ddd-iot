package io.ac.iot.servicetenant.client;

import common.rest.RestResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-25
 **/
@FeignClient(name = "service-tenant",path = "/role")
public interface RoleClient {

    /**
     * 删除时效菜单
     * @param menuId
     */
    @DeleteMapping("invalid-menu")
    RestResult clearInvalidMenu(@RequestParam("menuId") String menuId);
}
