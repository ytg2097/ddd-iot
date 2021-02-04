package io.ac.apigatewayplatform.security;

import common.exception.BusinessException;
import common.rest.RestResult;
import io.ac.apigatewayplatform.common.util.NetworkUtil;
import io.ac.apigatewayplatform.security.manager.AuthorizationManager;
import io.ac.iot.servicetenant.client.AccountClient;
import io.ac.iot.servicetenant.representation.AccountAuthRepresentation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

import static common.rest.RestStatus.CODE_SUCCESS;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-13
 **/
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class SecurityController {

    private final AccountClient accountClient;
    private final AuthorizationManager authManager;

    @PostMapping("login")
    public Mono<RestResult<User>> login(@RequestBody @Valid SignInParam signInParam, ServerHttpRequest request){

        RestResult<AccountAuthRepresentation> result = accountClient.valid(signInParam.getLoginName(), signInParam.getPassword(), "PLATFORM", NetworkUtil.getIP(request));
        if (result.getStatus().getCode() != CODE_SUCCESS){
             throw new BusinessException(result.getStatus().getCode(),result.getStatus().getMsg());
        }
        AccountAuthRepresentation account = result.getResult();
        User user = authManager.remember(account);
        return Mono.just(new RestResult<>(user));
    }

    @DeleteMapping("logout")
    public Mono<RestResult<Boolean>> logout(@RequestHeader("operatorToken") String token){

        return Mono.just(new RestResult<>(authManager.forget(token)));
    }
}
