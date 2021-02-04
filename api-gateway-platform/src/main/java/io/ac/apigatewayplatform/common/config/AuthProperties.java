package io.ac.apigatewayplatform.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-03-05
 **/
@Component
@ConfigurationProperties(prefix = "gateway")
@Getter
@Setter
public class AuthProperties {

    private List<String> whiteList;

    private Boolean auth;

}
