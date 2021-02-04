package io.ac.iot.adapter.rest.command;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-22
 **/
@Getter
@Setter
public class DeviceTagCreateCommand {

    @NotNull
    private String name;

    @NotNull
    @Pattern(regexp = "^\\\\w+$",message = "标签值只能为数字,中文,下划线")
    private String value;
}
