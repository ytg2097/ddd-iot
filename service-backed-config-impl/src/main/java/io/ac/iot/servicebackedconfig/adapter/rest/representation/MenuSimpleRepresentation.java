package io.ac.iot.servicebackedconfig.adapter.rest.representation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-30
 **/
@Getter
@Setter
public class MenuSimpleRepresentation {

    private String id;

    private String name;

    private Boolean visible;

    private List<MenuSimpleRepresentation> children;

}
