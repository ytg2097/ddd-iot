package io.ac.iot.servicedatahub;

import io.ac.starter.feignclient.FeignParamValueProvider;
import io.ac.starter.feignclient.InnerHooker;
import io.ac.starter.util.RequestHeadHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import static io.ac.starter.util.RequestHeadHolder.RequestHead.OPERATOR_ID;
import static io.ac.starter.util.RequestHeadHolder.RequestHead.TENANT_ID;
import static java.util.Optional.ofNullable;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-24
 **/
@EnableEurekaClient
@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients(basePackages = "io.ac.**.client")
@EnableBinding
public class ApplicationStarter {

    public static void main(String[] args){

        SpringApplication.run(ApplicationStarter.class, args);
    }

    @Bean
    public FeignParamValueProvider feignParamValueProvider(){

        return new FeignParamValueProvider(){

            @Override
            public String findOperatorId() {
                return ofNullable(RequestHeadHolder.find(OPERATOR_ID)).orElse(DEFAULT_OPERATOR_ID);
            }

            @Override
            public String findTid() {
                return ofNullable(RequestHeadHolder.find(TENANT_ID)).orElse(DEFAULT_TENANT_ID);
            }
        };
    }

    @Bean
    public InnerHooker innerHooker(){

        return new InnerHooker() {
        };
    }

}
