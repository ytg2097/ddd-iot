package io.ac.apigatewayplatform.router;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.time.ZonedDateTime.now;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-18
 **/
@Configuration
public class OrderRouter {
 /*   @Bean
    public RouteLocator orderRouterLocator(RouteLocatorBuilder builder){

        return builder.routes()
                .route(r -> r.after(now()).uri("/order")).build();
    }*/
}
