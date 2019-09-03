package com.yangyi.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @EnableEurekaServer ：启用 Eureka-Server，指明该服务是 server
 */
@EnableEurekaServer
@SpringBootApplication
public class EurekaServer7001Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServer7001Application.class, args);
	}

}

