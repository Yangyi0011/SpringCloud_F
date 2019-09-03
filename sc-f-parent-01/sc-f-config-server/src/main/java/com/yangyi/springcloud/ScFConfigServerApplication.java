package com.yangyi.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

//指定为 Eureka-Client 注册进 Eureka 集群，可配置多个 Config-Server 保证高可用
@EnableEurekaClient
@EnableConfigServer	//分布式配置中心 Config-Server
@SpringBootApplication
public class ScFConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ScFConfigServerApplication.class, args);
	}

}

