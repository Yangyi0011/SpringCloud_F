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