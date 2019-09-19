package com.yangyi.springcloud.service.impl;

import com.yangyi.springcloud.dao.EmployeeMapper;
import com.yangyi.springcloud.entity.Employee;
import com.yangyi.springcloud.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YangYi
 * Date: 2019/2/12
 * Time: 0:06
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
    @Qualifier("employeeMapper")
    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee getEmpById(Integer id) {
        return employeeMapper.findOne(id);
    }

    @Override
    public List<Employee> getEmps() {
        /**
         * 模拟 Hystrix 服务熔断：
         *      即当各个服务之间在进行相互调用时，某个服务的某个功能突然出现异常了，
         *      会导致其他服务对该服务该功能的调用出现阻塞，
         *      Hystrix 为了使整个系统不这种阻塞拖崩溃，会自动熔断该功能的调用，
         *      采用 fallback 指定的处理类去处理，然后返回一个符合预期的提示信息给用户。
         */
        int i = 1/0;
        return employeeMapper.findAll();
    }

    @Override
    public Integer addEmp(Employee employee) {
        return employeeMapper.add(employee);
    }

    @Override
    public Integer updateEmp(Employee employee) {
        return employeeMapper.update(employee);
    }

    @Override
    public Integer deleteEmpById(Integer id) {
        return employeeMapper.delete(id);
    }
}