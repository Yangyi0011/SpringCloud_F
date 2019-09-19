package com.yangyi.springcloud.service;

import com.yangyi.springcloud.entity.Employee;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YangYi
 * Date: 2019/2/12
 * Time: 0:04
 */
public interface EmployeeService {

    public Employee getEmpById(Integer id);

    public List<Employee> getEmps();

    public Integer addEmp(Employee employee);

    public Integer updateEmp(Employee employee);

    public Integer deleteEmpById(Integer id);
} 