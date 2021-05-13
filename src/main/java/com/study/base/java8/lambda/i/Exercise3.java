package com.study.base.java8.lambda.i;

@FunctionalInterface
public interface Exercise3<T, R> {

    R getValue(T t1, T t2);
}
