package io.ac.iot.servicelog.tenantoperationlog;

import common.rest.PageableRestResult;
import io.ac.iot.servicelog.tenantoperationlog.request.TenantOperationLogListRequestParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-23
 **/
@RequestMapping("tenant-operation-log")
@RestController
@Api("租户操作日志")
public class TenantOperationLogController {

    private TenantOperationLogService service;

    public TenantOperationLogController(TenantOperationLogService service) {
        this.service = service;
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("list")
    public PageableRestResult list(TenantOperationLogListRequestParam requestParam){

        return service.list(requestParam);
    }
}
