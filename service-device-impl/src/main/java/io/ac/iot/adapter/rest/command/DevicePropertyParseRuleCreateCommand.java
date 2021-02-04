package io.ac.iot.adapter.rest.command;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-02
 **/
@Getter
@Setter
public class DevicePropertyParseRuleCreateCommand {

    @NotBlank
    private String key;

    @NotNull
    private Integer startPoint;

    @NotNull
    private Integer step;

    private String type;

    @NotNull
    private String name;
}
