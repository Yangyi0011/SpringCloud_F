package com.yangyi.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

@MapperScan({"com.yangyi.springcloud.dao"})//指定要扫描的mapper
@RestController
//@EnableDiscoveryClient  //服务发现【只有服务消费者需要】
@EnableEurekaClient //指明自己是 Eureka-Client
@SpringBootApplication
public class ServiceProviderEmployee8001Application {

    public static void main(String[] args) {
        SpringApplication.run(ServiceProviderEmployee8001Application.class, args);
    }

//    /**
//     * 连接测试
//     */
//    @GetMapping("/test")
//    public Employee test() {
//        Employee employee = new Employee();
//        employee.setId(1);
//        employee.setLastName("Test");
//        employee.setAge(18);
//        employee.setGender(1);
//        employee.setDb_source("test");
//        return employee;
//    }
}

