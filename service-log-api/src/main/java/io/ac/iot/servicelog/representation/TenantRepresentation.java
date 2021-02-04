package io.ac.iot.servicelog.representation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-25
 **/
@Getter
@Setter
@EqualsAndHashCode
public class TenantRepresentation {

    private String id;

    private String name;
}
