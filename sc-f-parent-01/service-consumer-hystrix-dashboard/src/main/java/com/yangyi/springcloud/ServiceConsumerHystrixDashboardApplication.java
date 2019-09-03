package com.yangyi.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * 可以在此监控支持 HystrixDashboard 的其他服务
 */
@EnableHystrixDashboard	//Hystrix监控
@SpringBootApplication
public class ServiceConsumerHystrixDashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceConsumerHystrixDashboardApplication.class, args);
	}
}

