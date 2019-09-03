package com.yangyi.springcloud;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;

@EnableHystrixDashboard //Hystrix 监控支持
@EnableCircuitBreaker	//支持服务熔断
@EnableHystrix	//支持 Hystrix
@MapperScan({"com.yangyi.springcloud.dao"})//指定要扫描的mapper
@EnableDiscoveryClient  //服务发现
@EnableEurekaClient //指明自己是 Eureka-Client
@SpringBootApplication
public class ServiceProviderEmployeeHystrixApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceProviderEmployeeHystrixApplication.class, args);
	}

    /**
     * 将一个 HystrixMetricsStreamServlet 注册进IOC容器中，
     * 解决 Unable to connect to Command Metric Stream 问题
     * @return
     */
    @Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/actuator/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }

}

