package io.ac.iot.adapter.rest.command;

import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-04-02
 **/
@Getter
@Setter
public class ManufacturerCreateCommand {

    private String name;

    private String description;

    private String phone;

    private String email;
}
