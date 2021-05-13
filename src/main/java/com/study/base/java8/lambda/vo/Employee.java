package com.study.base.java8.lambda.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Employee implements Serializable {

    private String name;

    private Integer age;

    public Employee() {}

    public Employee(String name, Integer age){
        this.name = name;
        this.age = age;
    }
}
