package io.ac.iot.adapter.rest.command;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-22
 **/
@Getter
@Setter
public class DeviceCapabilityInfoModifyCommand {

    @NotNull
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private String description;
}
