package com.yangyi.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@EnableHystrixDashboard //Hystrix 监控支持
@EnableCircuitBreaker    //支持服务熔断
@EnableHystrix	//支持 Hystrix
//启用 feign，指明 FeignClients 所在的包
@EnableFeignClients(basePackages = {"com.yangyi.springcloud"})
@EnableEurekaClient	// 指明自己是 Eureka-Client
@EnableDiscoveryClient	// 向服务中心注册自己
@SpringBootApplication
public class ServiceConsumerFeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceConsumerFeignApplication.class, args);
	}

    /**
     * 已被提取到 sc-f-api 当做公告模块
     * 将一个 HystrixMetricsStreamServlet 注册进IOC容器中，
     * 解决 Unable to connect to Command Metric Stream 问题
     */
//    @Bean
//    public ServletRegistrationBean getServlet(){
//        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
//        registrationBean.setLoadOnStartup(1);
//        registrationBean.addUrlMappings("/hystrix.stream");
//        registrationBean.setName("HystrixMetricsStreamServlet");
//        return registrationBean;
//    }
}

