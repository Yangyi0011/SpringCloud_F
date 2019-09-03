package com.yangyi.springcloud.controller;

import com.yangyi.springcloud.entities.Employee;
import com.yangyi.springcloud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

    @GetMapping("/employee/{id}")
    public Employee getEmpById(@PathVariable("id") Integer id){
        Employee employee = employeeService.getEmpById(id);
        return employee;
    }

    @GetMapping("/employees")
    public List<Employee> getEmps(){
        return employeeService.getEmps();
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