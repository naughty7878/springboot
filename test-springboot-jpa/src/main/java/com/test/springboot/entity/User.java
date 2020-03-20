package com.test.springboot.entity;

import org.springframework.context.annotation.Configuration;

import javax.persistence.*;

// 使用JAP注解配置映射关系

// 告诉JPA这是一个实体类（和数据表映射的类）
@Entity
// @Table来指定和那个数据表对应；如果省略默认表名就是user;
@Table(name="jpa_user")
public class User {

    // 这是一个主键
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "last_name", length = 50)
    private String lastName;

    // 省略默认列名就是属性名
    @Column
    private String email;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
