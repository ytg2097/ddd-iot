package io.ac.apigatewayplatform.security;

import io.ac.iot.servicetenant.representation.AccountAuthRepresentation;
import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-13
 **/
@Data
public class User implements Serializable {

    private String accountId;

    private String tenantId;

    private String loginName;

    private String token;

    public static User of(AccountAuthRepresentation account){

        User user = new User();
        user.setAccountId(account.getId());
        user.setTenantId(account.getTenantId());
        user.setLoginName(account.getLoginName());
        return user;
    }
}
