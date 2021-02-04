package io.ac.iot.adapter.rest.command;

import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-01
 **/
@Getter
@Setter
public class DeviceCapabilityCreateCommand {

    private String name;

    private String description;
}
