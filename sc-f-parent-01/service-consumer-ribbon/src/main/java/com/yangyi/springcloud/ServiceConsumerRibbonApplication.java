package com.yangyi.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableHystrixDashboard //支持 Hystrix 监控
@EnableCircuitBreaker	//支持 Hystrix 服务熔断
@EnableHystrix	//启用 Hystrix 服务熔断器，避免服务相互调用时引发雪崩。
@EnableDiscoveryClient //将自己注册到 Eureka 服务中心
@EnableEurekaClient	//指明自己是 Eureka-Client
@SpringBootApplication
public class ServiceConsumerRibbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceConsumerRibbonApplication.class, args);
	}
}

