package com.yangyi.springcloud.service;

import com.yangyi.springcloud.entities.Employee;
import com.yangyi.springcloud.service.impl.EmployeeClientService_Hystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YangYi
 * Date: 2019/2/13
 * Time: 23:06
 */

/**
 * 指明为 Feign 客户端，并指定要连向哪个服务.
 *  fallback：指向 Hystrix 服务熔断快速错误处理类，该类需要实现本接口。
 *  fallbackFactory 与 fallback 原理一样，只是 fallbackFactory 是一个工厂方法。
 */
@FeignClient(value = "SERVICE-PROVIDER", fallback = EmployeeClientService_Hystrix.class)
public interface EmployeeClientService {

    //    @GetMapping("/employee/{id}")，低版本的 Feign 可能不支持
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET)
    public Employee getEmpById(@PathVariable("id") Integer id);

    //    @GetMapping("/employees")，低版本的 Feign 可能不支持
    @RequestMapping(value = "/employees", method = RequestMethod.GET)
    public List<Employee> getEmps();

    //    @PostMapping("/employee")，低版本的 Feign 可能不支持
    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public Employee addEmp(@RequestParam("employee") Employee employee);

    //    @PutMapping("/employee")，低版本的 Feign 可能不支持
    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public String updateEmp(@RequestParam("employee") Employee employee);

//    @DeleteMapping("/employee/{id}")，低版本的 Feign 可能不支持
    @RequestMapping(value = "/employee/{id}",method = RequestMethod.DELETE)
    public String deleteEmp(@PathVariable("id") Integer id);
}