package com.study.base.avoid_resubmit.annotate;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PreventDuplication {
    /**
     * 防重复操作限时标记数据（存储redis限时标记数据）
     * @return String
     */
    String value() default "value";

    /**
     * 防重复操作过期时间（借助redis实现限时控制）
     * @return long
     */
    long expireSeconds() default 10;
}
