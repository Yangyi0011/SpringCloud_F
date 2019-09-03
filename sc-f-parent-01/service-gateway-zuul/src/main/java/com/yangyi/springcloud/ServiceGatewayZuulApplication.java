package com.yangyi.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableZuulProxy    // Zuul路由网关
@EnableDiscoveryClient  //服务发现
@EnableEurekaClient // Eureka-Client
@SpringBootApplication
public class ServiceGatewayZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceGatewayZuulApplication.class, args);
	}

}

