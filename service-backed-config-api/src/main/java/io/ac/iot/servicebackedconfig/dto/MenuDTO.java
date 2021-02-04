package io.ac.iot.servicebackedconfig.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-25
 **/
@Getter
@Setter
@ToString
public class MenuDTO {

    private String menuId;

    private String name;

    private String path;

    private String icon;

    private String redirect;

    private String component;

    private Boolean create;

    private Boolean modify;

    private Boolean delete;

    private Boolean hidden;

    private List<MenuDTO> children;
}
