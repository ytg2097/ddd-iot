package io.ac.iot.servicelog.representation;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-13
 **/
@Getter
@Setter
@EqualsAndHashCode
public class EmployeeRepresentation {

    private String name;

    private String phone;

    private Long id;

    private String tenantId;
}
