package io.ac.iot.servicetenant.common.config;

import io.ac.iot.servicetenant.common.util.RequestIdHolder;
import io.ac.starter.feignclient.FeignParamValueProvider;
import io.ac.starter.feignclient.InnerHooker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.util.Optional.ofNullable;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-06-12
 **/
@Configuration
public class FeignConfig {

    @Bean
    public FeignParamValueProvider feignParamValueProvider(){

        return new FeignParamValueProvider(){

            @Override
            public String findOperatorId() {
                return ofNullable(RequestIdHolder.opId.find()).orElse(DEFAULT_OPERATOR_ID);
            }

            @Override
            public String findTid() {
                return ofNullable(RequestIdHolder.tid.find()).orElse(DEFAULT_TENANT_ID);
            }
        };
    }

    @Bean
    public InnerHooker innerHooker(){

        return new InnerHooker() {
        };
    }

}
