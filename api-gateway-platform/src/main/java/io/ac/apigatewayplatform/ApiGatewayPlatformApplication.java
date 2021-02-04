package io.ac.apigatewayplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "io.ac.**.client")
public class ApiGatewayPlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayPlatformApplication.class, args);
    }



}
