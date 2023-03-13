package com.study.logaop.annotate;


import java.lang.annotation.*;

/**
 * AOP日志记录 自定义注解类
 * Author 隋伟东
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationAnnotation {

    /**
     * 内容
     */
    String content() default "";

    /**
     * 系统类型
     */
    int sysType() default 0;

    /**
     * 操作类型  0登录  1增加  2删除  3修改  4查询
     */
    int optType() default 0;

    /**
     * 功能名称
     */
    String action() default "";


}
