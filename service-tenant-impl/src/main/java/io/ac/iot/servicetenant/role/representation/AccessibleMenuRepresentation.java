package io.ac.iot.servicetenant.role.representation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-07-01
 **/
@Getter
@Setter
public class AccessibleMenuRepresentation {

    private String id;

    private String name;

    private Boolean create;

    private Boolean modify;

    private Boolean delete;

    private Boolean view;

    private List<AccessibleMenuRepresentation> children;
}
