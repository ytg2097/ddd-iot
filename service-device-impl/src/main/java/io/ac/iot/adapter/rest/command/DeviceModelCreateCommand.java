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
public class DeviceModelCreateCommand {

    private String model;

    private String manufacturerId;

    private String protocolType;

    private String pic;

    private String description;
}
