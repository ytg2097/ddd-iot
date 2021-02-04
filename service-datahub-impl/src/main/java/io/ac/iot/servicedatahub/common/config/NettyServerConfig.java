package io.ac.iot.servicedatahub.common.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-11-18
 **/
@Component
@ConfigurationProperties(prefix = "netty.config")
@Getter
@Setter
public class NettyServerConfig {

    private String host;

    private Integer port;
}
