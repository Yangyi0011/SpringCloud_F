package com.yangyi.springcloud.config;

/**
 * Created by IntelliJ IDEA.
 * User: YangYi
 * Date: 2019/2/13
 * Time: 14:52
 */

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.yangyi.myrule.RoundRobinRule_YY;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Ribbon配置类。
 * 注意：在引入 sc-f-api 的公共模块时，此配置类的类名不能与公共模块中，
 *  config 包下的配置类名相同，否则此处的配置会覆盖掉公共模块中的配置。
 */
@Configuration
public class ConfigBean_Ribbon {

    /**
     * 工程启动时想 IOC 容器注册 RestTemplate Bean，
     * @LoadBalanced ：开启客户端负载均衡【生效前提：需要开启多个服务提供者】
     *  常用负载均衡策略：
     *      1、随机策略
     *          RandomRule：随机策略很简单，就是从服务器中随机选择一个服务器。
     *
     *      2、轮询策略
     *          RoundRobinRule：轮询抽取，每次都取下一个服务器。
     *              - WeightedResponseTimeRule：
     *                  根据响应时间计算权重，响应越快权重越大，越优先被选中。
     *                  刚启动时没有计算出权重大小，则使用父类的轮询策略。
     *
     *              - ResponseTimeWeightedRule：
     *
     *          AvailabilityFilteringRule：
     *              会过滤掉由于多次访问故障而处于断路器跳闸状态的服务，
     *              还有并发的连接数量超过阈值的服务，最后对剩余的服务启用轮询算法。
     *
     *          BestAvailableRule：
     *             会先过滤掉由于多次访问故障而处于断路器跳闸状态的服务，
     *             然后选择一个并发量最小的服务。
     *
     *          RetryRule：
     *              先按照轮询算法获取服务，若获取失败，则在指定时间内自动过滤掉获取失败的服务。
     *
     *          ZoneAvoidanceRule：【默认规则】
     *              根据复合判断Server所在区域的性能和Server的可用性进行选择。
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * 使用自定义负载均衡算法代替默认的轮询负载均衡算法
     */
    @Bean
    public IRule iRule(){
        //return new RandomRule();//使用客户端随机负载均衡算法

        /**
         * 使用自定义的客户端负载均衡算法：
         *  【前提】：需要在主启动类加上 @RibbonClient(name="SERVICE-PROVIDER",configuration=MySelfRule.class)
         *          name：要在哪个服务上添加自定义的负载均衡算法,写上服务的名字。
         *          configuration：我自定义的负载均衡算法类。
         */
//        return new RoundRobinRule_YY();

        return new RetryRule();
    }

//    /**
//     * 已提取到 sc-f-api 中作为通用主件
//     * 将一个 HystrixMetricsStreamServlet 注册进IOC容器中，
//     * 解决 Unable to connect to Command Metric Stream 问题
//     * @return
//     */
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