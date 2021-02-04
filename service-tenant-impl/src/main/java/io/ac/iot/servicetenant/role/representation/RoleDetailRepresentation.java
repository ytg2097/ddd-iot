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
public class RoleDetailRepresentation {

    private String id;

    private String name;

    private String description;

    private List<AccessibleMenuRepresentation> resources;
}
