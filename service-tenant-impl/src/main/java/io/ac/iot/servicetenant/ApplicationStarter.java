package io.ac.iot.servicetenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @description:
 * @author: yangtg
 * @create: 2020-02-24
 **/
@EnableDiscoveryClient
@SpringBootApplication
@EnableJpaAuditing
@EnableFeignClients(basePackages = "io.ac.**.client")
public class ApplicationStarter {

    public static void main(String[] args){

        SpringApplication.run(ApplicationStarter.class, args);
    }


}
