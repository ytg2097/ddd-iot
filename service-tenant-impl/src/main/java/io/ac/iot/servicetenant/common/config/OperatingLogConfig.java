package io.ac.iot.servicetenant.common.config;

import io.ac.iot.servicelog.OperationLogAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-29
 **/
@Configuration
public class OperatingLogConfig {

    @Bean
    public OperationLogAspect operationLogAspect(){

        return new OperationLogAspect();
    }
}
