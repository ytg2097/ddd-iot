package io.ac.iot.adapter.rest.command;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-17
 **/
@Getter
@Setter
@ApiModel("规则创建命令")
public class RuleCreateCommand {

    private String name;

    private Boolean mon;

    private Boolean tue;

    private Boolean wed;

    private Boolean thu;

    private Boolean fri;

    private Boolean sat;

    private Boolean sun;

    private String startTime;

    private String endTime;

    private String deviceModelId;

    private String deviceGroupId;

    private List<String> deviceIds;

    private String expression;
}
