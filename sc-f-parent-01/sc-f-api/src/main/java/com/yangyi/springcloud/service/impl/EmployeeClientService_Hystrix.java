package com.yangyi.springcloud.service.impl;

import com.yangyi.springcloud.entities.Employee;
import com.yangyi.springcloud.service.EmployeeClientService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: YangYi
 * Date: 2019/2/14
 * Time: 19:06
 */

/**
 * 在 Feign 中通过 Hystrix 来实现服务熔断、服务降级机制，
 * 只需要实现 @FeignClient(value=服务名,) 所标注的接口，
 *  且将 fallback 指向此类就行。
 *
 * 服务熔断：【在服务端处理】
 *  即正常运行的服务调用中，某个功能因发生了异常会导致调用该功能的其他服务出现阻塞现象，
 *  Hystrix 为了整个系统不被这些阻塞给拖死【崩溃】，会用 fallback 指定的类进行快速错误处理，
 *  返回一个符合预期的信息给用户，而不用等待服务超时。
 *
 * 服务降级：【在客户端处理】
 *  在某些特殊时候(如双11)，为了给其他服务提供性能支持，
 *  会暂时关闭掉一些不那么必要的功能，如评论等。
 *  此时若还有用户访问这些被降级的服务，则 Hystrix 会通过 fallback 指定的类进行处理，
 *  返回一些友好的提示给用户，如“为了支持双11的稳定，评论功能暂时被关闭，xxx日之后再正常使用”等。
 *  避免用户不知情况重复尝试，耗费系统资源。
 *
 *  服务熔断与服务降级的区别：
 *      熔断是对服务部分功能的错误或是异常的处理，在执行方法出错时，
 *  Hystrix 会通过fallback 指定的类来直接返回预期设定的信息，不用等到超时
 *  避免调用该功能的其他服务阻塞，造成服务器崩溃。
 *      而服务降级是为了给其他服务提供性能支持，会手动关闭一些不必要的服务，
 *  这些服务被关闭后，Hystrix 通过fallback 指定的类来返回预期设定的信息，
 *  给用户以友好提示，避免用户不知情况重复尝试，耗费系统资源。。
 */
@Component  //需要将 HyStrix 错误处理类加入 SpringIOC 容器
public class EmployeeClientService_Hystrix implements EmployeeClientService {

    /**
     * 服务降级处理
     */
    @Override
    public Employee getEmpById(Integer id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setLastName("错误");
        employee.setGender(1);
        employee.setAge(0);
        employee.setDbSource("十分抱歉，由于XXX原因，我们暂时关闭了该功能，给您带来不便，深表歉意。");
        return employee;
    }

    /**
     * 服务熔断处理
     * @return
     */
    @Override
    public List<Employee> getEmps() {
        Employee employee = new Employee();
        employee.setId(0);
        employee.setLastName("错误");
        employee.setGender(1);
        employee.setAge(0);
        employee.setDbSource("十分抱歉，请求出错了，请稍后再重试");

        List<Employee> list = new ArrayList<>();
        list.add(employee);
        return list;
    }

    @Override
    public Employee addEmp(Employee employee) {
        employee.setId(0);
        employee.setLastName("错误");
        employee.setGender(1);
        employee.setAge(0);
        employee.setDbSource("十分抱歉，请求出错了，请稍后重试");
        return employee;
    }

    @Override
    public String updateEmp(Employee employee) {
        return "十分抱歉，请求出错了，请稍后重试";
    }

    @Override
    public String deleteEmp(Integer id) {
        return "十分抱歉，请求出错了，请稍后重试";
    }
}