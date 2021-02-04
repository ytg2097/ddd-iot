package io.ac.iot.servicetenant.client;

import common.rest.RestResult;
import io.ac.iot.servicetenant.representation.AccountAuthRepresentation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-26
 **/
@FeignClient(name = "service-tenant",path = "/account")
public interface AccountClient {

    /**
     * 验证账号是否有效
     * @param loginName
     * @param password
     * @param accountType
     * @return
     */
    @GetMapping("/valid")
    RestResult<AccountAuthRepresentation> valid(@RequestParam("loginName") String loginName,
                                                @RequestParam("password") String password,
                                                @RequestParam("accountType") String accountType,
                                                @RequestParam("ip") String ip);

    /**
     * 激活租户下所有账号
     * @param tenantId
     * @return
     */
    @PutMapping("active")
    RestResult active(@RequestParam("tenantId") String tenantId);

    /**
     * 失效租户下所有账号
     * @param tenantId
     * @return
     */
    @PutMapping("inactive")
    RestResult inactive(@RequestParam("tenantId") String tenantId);

    /**
     * 根据登陆名获取所属租户id
     * @param loginName
     * @return
     */
    @GetMapping("/tenant-id")
    RestResult<String> getTenantId(@RequestParam("loginName") String loginName);
}
