package io.ac.iot.adapter.rest;

import common.rest.RestResult;
import io.ac.iot.ruleengine.RuleEngineService;
import io.ac.iot.adapter.rest.command.RuleCreateCommand;
import io.ac.iot.adapter.rest.command.UserCreateCommand;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-24
 **/
@RestController
@RequestMapping("rule-engine")
@RequiredArgsConstructor
public class RuleEngineController {

    private final RuleEngineService ruleEngineService;

    @ApiOperation("新增规则引擎")
    @PostMapping
    public RestResult<String> create(@RequestBody RuleCreateCommand command){

        String id = ruleEngineService.create(command);
        return new RestResult<>(id);
    }

    @ApiOperation("新增用户, 用于触发告警时接收短信或者邮件")
    @PostMapping("user")
    public RestResult<String> addUser(@RequestBody UserCreateCommand createCommand){

        String id = ruleEngineService.addUser(createCommand);
        return new RestResult<>(id);
    }
}
