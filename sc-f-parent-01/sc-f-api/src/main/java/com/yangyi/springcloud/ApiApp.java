package com.yangyi.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Created by IntelliJ IDEA.
 * User: YangYi
 * Date: 2019/2/3
 * Time: 0:35
 */

@EnableFeignClients
@SpringBootApplication
public class ApiApp {
    public static void main(String[] args) {
        SpringApplication.run(ApiApp.class,args);
    }
}
