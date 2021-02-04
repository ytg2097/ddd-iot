package io.ac.apigatewayplatform.filter;

import common.exception.BusinessException;
import io.ac.apigatewayplatform.common.config.AuthProperties;
import io.ac.apigatewayplatform.security.manager.AuthorizationManager;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import static common.WebConst.REQUEST_HEADER_OPERATOR_TOKEN;
import static org.apache.commons.lang3.StringUtils.isEmpty;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-05
 **/
@Component
public class OperatorTokenFilter implements GlobalFilter , Ordered {

    private final String SWAGGER = "swagger";
    private final String API_DOCS = "api-docs";
    private final AuthProperties authProperties;
    private final AuthorizationManager authorizationManager;

    public OperatorTokenFilter(AuthProperties authProperties, AuthorizationManager authorizationManager) {
        this.authProperties = authProperties;
        this.authorizationManager = authorizationManager;
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        if (!needAuth(exchange)){
            return chain.filter(exchange);
        }

        String token = exchange.getRequest().getHeaders().getFirst(REQUEST_HEADER_OPERATOR_TOKEN);

        if(isEmpty(token)){
            throw new BusinessException(UNAUTHORIZED.value(),"令牌无效");
        }

        if (!authorizationManager.valid(token)){
            throw new BusinessException(UNAUTHORIZED.value(),"令牌无效");
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -100;
    }

    private boolean needAuth(ServerWebExchange exchange){

        String path = exchange.getRequest().getURI().getPath();

        if(path.contains(SWAGGER) || path.contains(API_DOCS)){
            return false;
        }

        return authProperties.getAuth() && !authProperties.getWhiteList().contains(path);
    }
}
