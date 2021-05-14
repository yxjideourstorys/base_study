package com.study.base.java8.vo;

import lombok.Data;

/**
 * 交易类
 */
@Data
public class Transaction {

    private Trader trader;

    private int year;

    private int value;

    public Transaction(){}

    public Transaction(Trader trader, int year, int value) {
        this.trader = trader;
        this.year = year;
        this.value = value;
    }
}
