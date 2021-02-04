package io.ac.iot.servicebackedconfig.adapter.rest.command;

import lombok.Data;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-08-28
 **/
@Data
public class MenuCreateCommand {

    private String parentId;

    private String name;

    private String path;

    private Integer sort;

    private String component;

    private String redirect;

    private String icon;

    private Boolean hidden;
}
