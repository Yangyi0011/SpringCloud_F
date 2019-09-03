package com.yangyi.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;

/**
 * 使用Ribbon自定义客户端负载均衡算法：
 * 【前提】：不能放在 @ComponentScan 所扫描的包下，
 *  即不能 SpringBoot 主启动类所在的包及其子包下
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule iRule() {
        //return new RandomRule();// Ribbon默认是轮询，我自定义为随机
        //return new RoundRobinRule();// Ribbon默认是轮询，我自定义为随机

        return new RoundRobinRule_YY();// 我自定义为每台机器5次
    }
}
