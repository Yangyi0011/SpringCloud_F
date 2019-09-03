package com.yangyi.springcloud.dao;

import com.yangyi.springcloud.entities.Employee;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YangYi
 * Date: 2019/2/11
 * Time: 23:34
 */
@Repository("employeeMapper")
public interface EmployeeMapper {

    public Employee findOne(@Param("id") Integer id);

    public List<Employee> findAll();

    public Integer add(@Param("employee") Employee employee);

    public Integer update(@Param("employee") Employee employee);

    public Integer delete(@Param("id") Integer id);
} 