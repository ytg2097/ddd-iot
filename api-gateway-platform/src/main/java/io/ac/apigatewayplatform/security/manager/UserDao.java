package io.ac.apigatewayplatform.security.manager;

import common.JsonUtil;
import io.ac.apigatewayplatform.security.User;
import io.ac.iot.servicetenant.representation.AccountAuthRepresentation;
import io.ac.starter.redis.RedisClient;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static common.util.UUIDGenerator.gen36;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-13
 **/
@Component
public class UserDao {

    private final RedisClient redisClient;

    private final String PLATFORM_ID_KEY = "PLATFORM_ID_%s";
    private final String PLATFORM_TOKEN_KEY = "PLATFORM_TOKEN_%s";

    public UserDao(RedisClient redisClient) {
        this.redisClient = redisClient;
    }

    User insert(AccountAuthRepresentation account){

        check(account.getId());

        String tokenKey = String.format(PLATFORM_TOKEN_KEY, gen36());
        String idKey = String.format(PLATFORM_ID_KEY, account.getId());
        User user = User.of(account);
        user.setToken(tokenKey);
        synchronized (this) {
            redisClient.setWithTimeout(tokenKey, JsonUtil.toJson(user), 7200 * 1000L);
            redisClient.setWithTimeout(idKey, tokenKey, 7200 * 1000L);
        }
        return user;
    }

    User get(String token){

        String user;
        synchronized (this) {
            user = redisClient.get(token);
        }
        try {
            return JsonUtil.fromJson(user, User.class);
        } catch (Exception e) {
            return null;
        }
    }

    Boolean remove(String token){

        User user = get(token);

        if (Objects.nonNull(user)) {
            String idKey = String.format(PLATFORM_ID_KEY, user.getAccountId());

            synchronized (this) {
                redisClient.deleteKey(token);
                redisClient.deleteKey(idKey);
            }
        }
        return true;
    }

    private void check(String id) {

        String idKey = String.format(PLATFORM_ID_KEY, id);

        synchronized (this) {
            String tokenKey = redisClient.get(idKey);
            if (Objects.nonNull(tokenKey)) {
                redisClient.deleteKey(tokenKey);
                redisClient.deleteKey(idKey);
            }
        }

    }
}
