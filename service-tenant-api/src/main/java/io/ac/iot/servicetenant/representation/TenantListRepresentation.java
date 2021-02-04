package io.ac.iot.servicetenant.representation;

import lombok.Getter;
import lombok.Setter;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-10-23
 **/
@Getter
@Setter
public class TenantListRepresentation {

    private String name;

    private String id;

    private String setMealId;

    private String status;

    private String email;

    private String rootAccount;

    private String fixedPhone;
}
