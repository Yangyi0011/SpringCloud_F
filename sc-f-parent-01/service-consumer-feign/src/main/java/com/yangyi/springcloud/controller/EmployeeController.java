package com.yangyi.springcloud.controller;

import com.yangyi.springcloud.entities.Employee;
import com.yangyi.springcloud.service.EmployeeClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YangYi
 * Date: 2019/2/13
 * Time: 23:17
 */
@RestController
public class EmployeeController {

    /**
     * 编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，
     *  编译器感知不到，所以报错。
     *
     *  注：EmployeeClientService 是feign客户端，可能会有很多服务要调用，
     *      所以 EmployeeClientService 被抽取到 sc-f-api 里作为公共部分去了
     *
     * Feign在处理复杂参数时，会强制转为Post请求，即Feign对象传数不只支持Get请求。
     */
    @Autowired
    private EmployeeClientService employeeClientService;

    @GetMapping("/employee/{id}")
    public Employee getEmpById(@PathVariable("id") Integer id){
        Employee employee = employeeClientService.getEmpById(id);
        return employee;
    }

    @GetMapping("/employees")
    public List<Employee> getEmps(){
        List<Employee> emps = employeeClientService.getEmps();
        return emps;
    }

    @GetMapping("/employee")
    public Employee addEmp(Employee employee){
        Employee emp = employeeClientService.addEmp(employee);
        return emp;
    }

    @PutMapping("/employee")
    public String updateEmp(Employee employee){
        return employeeClientService.updateEmp(employee);
    }

    @DeleteMapping("/employee/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        return employeeClientService.deleteEmp(id);
    }
} 