package com.yangyi.springcloud.entities;

/**
 * Created by IntelliJ IDEA.
 * User: YangYi
 * Date: 2019/2/11
 * Time: 22:52
 */

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/*
链式风格编程：
    如同 newStringBuffer().append("aa").append("bb").append("cc");

   具体实现：
        在每一个 set() 方法后面加上 return this;

    如：
        public class Department implements Serializable {
            private String name;

            public Department setName( String name){
                this.name = name;
                return this;
            }
        }
    只要每个 set() 方法都这样写，那以后设值时就可以如此：
        dept.setName("xx").setXX().setXXX()....
 */
//@AllArgsConstructor //全参构造函数
@Accessors  //链式风格访问？
@NoArgsConstructor  //无参构造器
@Data   //生成每个属性的 get、set、toString、equals、hashCode 方法
public class Employee implements Serializable {
    private Integer id; // 主键
    private String lastName; // 员工名称
    private Integer gender; // 性别，1：男，0：女
    private Integer age;   //年龄
    private String dbSource;// 来自那个数据库，因为微服务架构可以一个服务对应一个数据库，同一个信息被存储到不同数据库
}