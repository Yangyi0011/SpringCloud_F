package com.yangyi.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.yangyi.springcloud.entity.Employee;
import com.yangyi.springcloud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YangYi
 * Date: 2019/2/12
 * Time: 0:09
 */

@RestController
public class EmployeeController {
    @Qualifier("employeeService")
    @Autowired
    private EmployeeService employeeService;

    @Value("${server.port}")    //从配置文件(环境)获取端口号
    private String port;

//    @GetMapping("/employee/{id}")
//    public Map<String,Object> hello(@PathVariable("id") Integer id){
//        Employee employee = employeeService.getEmpById(id);
//        //此处添加一个端口port，用来验证服务消费者的负载均衡是否开启
//        Map<String,Object> map = new ConcurrentHashMap<>();
//        map.put("port",port);
//        map.put("employee",employee);
//        return map;
//    }

    /**
     * @HystrixCommand : 指定该方法在服务之间请求调用失败时进行熔断，
     *          直接响应 fallbackMethod 指定的方法进行处理。
     */
    @HystrixCommand(fallbackMethod = "getEmpById_error")
    @GetMapping("/employee/{id}")
    public Employee getEmpById(@PathVariable("id") Integer id){
        Employee employee = employeeService.getEmpById(id);
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

    @HystrixCommand(fallbackMethod = "getEmps_error")
    @GetMapping("/employees")
    public List<Employee> getEmps(){
        return employeeService.getEmps();
    }

    public List<Employee> getEmps_error(){
        List<Employee> list = new ArrayList<>();
        Employee employee = new Employee();
        employee.setId(-1);
        employee.setLastName("错误");
        employee.setGender(1);
        employee.setAge(0);
        employee.setDbSource("十分抱歉，请求出错了，请稍后重试");
        list.add(employee);
        return list;
    }

    @GetMapping("/employee")
    public Employee addEmp(Employee employee){
        Integer res = employeeService.addEmp(employee);
        if (res > 0){
            return employee;
        }
        return null;
    }

    @PutMapping("/employee")
    public String updateEmp(Employee employee){
        Integer res = employeeService.updateEmp(employee);
        if (res > 0){
            return "更新成功";
        }else {
            return "更新失败";
        }
    }

    @DeleteMapping("/employee/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        Integer res = employeeService.deleteEmpById(id);
        if (res > 0){
            return "删除成功";
        }else {
            return "删除失败";
        }
    }
}