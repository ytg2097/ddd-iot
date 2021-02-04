package io.ac.iot.servicetenant.representation;

import lombok.Data;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-13
 **/
@Data
public class AccountAuthRepresentation {

    private String id;

    private String loginName;

    private String tenantId;
}
