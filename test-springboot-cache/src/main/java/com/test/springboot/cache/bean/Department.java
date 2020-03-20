package com.test.springboot.cache.bean;

import java.io.Serializable;

public class Department implements Serializable {

    private Integer id;
    private String depName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDepName() {
        return depName;
    }

    public void setDepName(String depName) {
        this.depName = depName;
    }
}
