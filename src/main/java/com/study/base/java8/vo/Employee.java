package com.study.base.java8.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Employee implements Serializable {

    private String name;

    private Integer age;

    private Integer salary;

    private Status status;

    public Employee() {}

    public Employee(Integer age){
        this.age = age;
    }

    public Employee(String name, Integer age){
        this.name = name;
        this.age = age;
    }

    public Employee(String name, Integer age, Integer salary){
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Employee(String name, Integer age, Integer salary, Status status) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.status = status;
    }

    public enum Status{
        FREE,
        BUSY,
        VOCATION;
    }
}
