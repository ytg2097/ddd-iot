package io.ac.iot.servicebackedconfig.adapter.rest.representation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-08-28
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuRepresentation {

    private String id;

    private String name;

    private String path;

    private Integer sort;

    private String icon;

    private Boolean visible;

    private String component;

    private String redirect;

    private Boolean hidden;

    private List<MenuRepresentation> children;
}
