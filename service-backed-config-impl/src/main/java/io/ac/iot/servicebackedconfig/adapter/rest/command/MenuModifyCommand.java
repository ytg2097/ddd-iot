package io.ac.iot.servicebackedconfig.adapter.rest.command;

import lombok.Data;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-08-28
 **/
@Data
public class MenuModifyCommand {

    private String name;

    private String path;

    private String id;

    private String icon;

    private String component;

    private String redirect;

    private Integer sort;

    private Boolean hidden;
}
