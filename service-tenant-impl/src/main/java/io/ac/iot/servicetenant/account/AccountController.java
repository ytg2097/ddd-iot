package io.ac.iot.servicetenant.account;

import common.rest.PageableRestResult;
import common.rest.RestResult;
import io.ac.iot.servicelog.Login;
import io.ac.iot.servicelog.OperationLog;
import io.ac.iot.servicetenant.account.command.AccountCreateCommand;
import io.ac.iot.servicetenant.account.command.AccountModifyCommand;
import io.ac.iot.servicetenant.account.command.PasswordModifyCommand;
import io.ac.iot.servicetenant.account.model.Account;
import io.ac.iot.servicetenant.account.request.AccountListRequestParam;
import io.ac.iot.servicetenant.client.AccountClient;
import io.ac.iot.servicetenant.representation.AccountAuthRepresentation;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static io.ac.iot.servicelog.OperationLog.Target.Origin.CUSTOMIZE;
import static io.ac.iot.servicelog.OperationLog.Target.Origin.METHOD_ARGS;
import static io.ac.iot.servicelog.OperationLog.Type.*;
import static java.util.stream.Collectors.toList;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-09
 **/
@RestController
@RequestMapping("/account")
@Api("账号API")
public class AccountController implements AccountClient{

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @ApiOperation(value = "创建普通账号", notes = "创建普通账号")
    @PostMapping("/")
    @OperationLog(type = OPERATE,target = @OperationLog.Target(origin = METHOD_ARGS,name="createCommand.loginName"),operating = "创建账号")
    public RestResult<String> createAccount(@RequestBody @Valid AccountCreateCommand createCommand){

        return new RestResult<>(accountService.createOrdinary(createCommand));
    }

    @ApiOperation(value = "修改账号信息", notes = "修改账号信息")
    @PutMapping
    @OperationLog(type = OPERATE,target = @OperationLog.Target(origin = METHOD_ARGS,name="modifyCommand.loginName"),operating = "修改账号信息")
    public RestResult modifyAccount(@RequestBody @Valid AccountModifyCommand modifyCommand){

        accountService.modify(modifyCommand);
        return new RestResult<>();
    }

    @ApiOperation(value = "验证账号", notes = "根据登录名, 密码, 账号类型查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName",  value = "登录名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password",  value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "accountType",  value = "账号所属平台", required = true, dataType = "String")
    })
    @GetMapping("/valid")
    @Override
    @Login
    @OperationLog(type = SAFETY,target = @OperationLog.Target(origin = CUSTOMIZE,name="AC Cloud"),operating = "登陆")
    public RestResult<AccountAuthRepresentation> valid(@RequestParam("loginName") String loginName,
                                                       @RequestParam("password") String password,
                                                       @RequestParam("accountType") String accountType,
                                                       @RequestParam("ip") String ip){

        return new RestResult<>(accountService.verifyLoginInfoAndMark(loginName,password,accountType,ip));
    }

    @ApiOperation(value = "失效指定租户下所有账号", notes = "失效指定租户下所有账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantId", value = "租户id", required = true, dataType = "String")
    })
    @PutMapping("inactive")
    @Override
    public RestResult inactive(@RequestParam("tenantId") String tenantId) {

        accountService.inactive(tenantId);
        return new RestResult();
    }

    @ApiOperation(value = "验证账号", notes = "根据登录名, 密码, 账号类型查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName",  value = "登录名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "password",  value = "密码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "accountType",  value = "账号所属平台", required = true, dataType = "String")
    })
    @GetMapping("/tenant-id")
    @Override
    public RestResult<String> getTenantId(@RequestParam("loginName")String loginName) {

        return new RestResult(accountService.getTenantId(loginName));
    }

    @ApiOperation(value = "激活指定租户下所有账号", notes = "激活指定租户下所有账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantId", value = "租户id", required = true, dataType = "String")
    })
    @PutMapping("active")
    @Override
    public RestResult active(@RequestParam("tenantId") String tenantId) {

        accountService.active(tenantId);
        return new RestResult();
    }

    @ApiOperation(value = "切换账号激活状态", notes = "切换账号激活状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String")
    })
    @PutMapping("/status/toggle")
    @OperationLog(type = OPERATE,target = @OperationLog.Target(origin = METHOD_ARGS,name="id"),operating = "切换账号激活状态")
    public RestResult toggleStatus(@RequestParam("id") String id) {

        accountService.toggleStatus(id);
        return new RestResult();
    }

    @ApiOperation(value = "删除账号", notes = "删除账号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "String")
    })
    @DeleteMapping
    @OperationLog(type = OPERATE,target = @OperationLog.Target(origin = METHOD_ARGS,name="id"),operating = "删除账号")
    public RestResult deleteAccount(@RequestParam("id") String id) {

        accountService.deleteAccount(id);
        return new RestResult();
    }

    @ApiOperation(value = "账号详情", notes = "账号详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "accountId", value = "accountId", required = true, dataType = "String")
    })
    @GetMapping("/{accountId}")
    public RestResult detail(@PathVariable("accountId") String accountId) {

        return new RestResult( accountService.detail(accountId));
    }

    @ApiOperation(value = "分页查询", notes = "分页查询")
    @GetMapping("list")
    @OperationLog(type = QUERY,target = @OperationLog.Target(origin = CUSTOMIZE,name="账号列表"),operating = "查询")
    public PageableRestResult listAccount(AccountListRequestParam param) {

        Page<Account> accounts = accountService.listAccount(param);
        return new PageableRestResult(accounts.get().map(Account::toRepresentation).collect(toList()),accounts.getTotalElements(),accounts.getTotalPages());
    }

    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PutMapping("password")
    public RestResult modifyPassword(@RequestBody @Valid PasswordModifyCommand modifyCommand){

        accountService.modifyPassword(modifyCommand);
        return new RestResult<>();
    }

}
