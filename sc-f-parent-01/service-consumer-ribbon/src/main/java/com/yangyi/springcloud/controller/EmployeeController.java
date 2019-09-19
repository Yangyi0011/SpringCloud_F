package com.yangyi.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yangyi.springcloud.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by IntelliJ IDEA.
 * User: YangYi
 * Date: 2019/2/12
 * Time: 1:10
 */
@RestController
public class EmployeeController {
    //Rest API 的请求前缀
    private static final String REST_URL_PREFIX = "http://SERVICE-PROVIDER";

    /**
     * 使用restTemplate访问restful接口非常的简单粗暴无脑。 (url, requestMap,
     * ResponseBean.class)这三个参数分别代表 REST请求地址、请求参数、HTTP响应转换被转换成的对象类型。
     */
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 通过 id 获取 Employee，测试负载均衡是否成功开启
     * @param id ：employee id
     */
//    @GetMapping("/employee/{id}")
//    public Map<String,Object> hello(@PathVariable("id") Integer id){
//        Map<String,Object> map = restTemplate.getForObject(REST_URL_PREFIX + "/employee/" + id, Map.class);
//        return map;
//    }

    /**
     * @HystrixCommand : 指定该方法在服务之间请求调用失败时进行熔断，
     *          直接响应 fallbackMethod 指定的方法进行处理。
     */
    @HystrixCommand(fallbackMethod = "getEmpById_error")
    @GetMapping("/employee/{id}")
    public Employee getEmpById(@PathVariable("id") Integer id){
        Employee employee = restTemplate.getForObject(REST_URL_PREFIX + "/employee/" + id, Employee.class);
        return employee;
    }

    /**
     * 处理 getEmpById() 请求错误时的快速失败响应，通过 Hystrix 来进行熔断处理。
     * @param id
     * @return 注意：返回类型、参数与 getEmpById() 必须保持一致！
     */
    public Employee getEmpById_error(@PathVariable("id") Integer id){
        Employee employee = new Employee();
        employee.setId(id);
        employee.setLastName("错误");
        employee.setGender(1);
        employee.setAge(0);
        employee.setDbSource("十分抱歉，请求出错了，请稍后重试");
        return employee;
    }
}
