package com.yangyi.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@EnableTurbine  // 监控多个 HystrixDashboard 服务
@EnableHystrixDashboard // HystrixDashboard 服务熔断监控
@EnableHystrix  // 服务熔断、降级
@EnableEurekaClient // Eureka-Client
@SpringBootApplication
public class ServiceConsumerHystrixTurbineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceConsumerHystrixTurbineApplication.class, args);
	}

}


