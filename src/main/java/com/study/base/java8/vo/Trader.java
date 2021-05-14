package com.study.base.java8.vo;

import lombok.Data;

/**
 * 交易员类
 */
@Data
public class Trader {

    private String name;

    private String city;

    public Trader(){}

    public Trader(String name, String city){
        this.name = name;
        this.city = city;
    }
}
