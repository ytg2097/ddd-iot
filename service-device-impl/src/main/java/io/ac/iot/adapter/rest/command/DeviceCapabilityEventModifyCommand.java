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
public class DeviceCapabilityEventModifyCommand {

    @NotNull
    private Integer capabilityId;

    //TODO
}
