package io.ac.apigatewayplatform.security.manager;

import io.ac.apigatewayplatform.security.User;
import io.ac.iot.servicetenant.representation.AccountAuthRepresentation;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-13
 **/
@Component
public class AuthorizationManager {

    private final UserDao userManager;

    public AuthorizationManager(UserDao userManager) {
        this.userManager = userManager;
    }

    public User remember(AccountAuthRepresentation account) {

        return userManager.insert(account);
    }

    public boolean forget(String token){

        return userManager.remove(token);
    }

    public boolean valid(String token) {

        return nonNull(userManager.get(token));
    }
}
