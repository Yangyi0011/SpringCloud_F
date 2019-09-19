package com.yangyi.springcloud.controller;

import com.yangyi.springcloud.entity.Employee;
import com.yangyi.springcloud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/employee/{id}")
    public Employee getEmpById(@PathVariable("id") Integer id){
        Employee employee = employeeService.getEmpById(id);
        return employee;
    }

    @GetMapping("/employees")
    public List<Employee> getEmps(){
        /**
         * 模拟 Hystrix 服务熔断：
         *      即当各个服务之间在进行相互调用时，某个服务的某个功能突然出现异常了，
         *      会导致其他服务对该服务该功能的调用出现阻塞，
         *      Hystrix 为了使整个系统不这种阻塞拖崩溃，会自动熔断该功能的调用，
         *      采用 fallback 指定的处理类去处理，然后返回一个符合预期的提示信息给用户。
         */
        int i = 1/0;
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