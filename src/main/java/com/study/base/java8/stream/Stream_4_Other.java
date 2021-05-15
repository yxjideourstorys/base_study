package com.study.base.java8.stream;

import com.study.base.java8.vo.Employee;

import java.util.Optional;

/**
 *      Optional 容器类的常用方法：
 *          Optional.of(T t) --- 创建一个Optional 实例
 *          Optional.empty() --- 创建一个空实例
 *          Optional.ofNullable(T t) --- 若 t 不为null，创建 Optional 实例，否则创建空实例
 *          isPresent() --- 判断是否包含值
 *          orElse(T t) --- 如果调用对象包含值，返回该值，否则返回t
 *          orElseGet(Supplier s) --- 如果调用对象包含值，返回该值，否则返回s获取的值
 *          map(Function f) --- 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty
 *          flatMap(Function mapper) --- 与 map 类似，要求返回值必须是 Optional
 */
public class Stream_4_Other {

    public void ofTest(){
        // Optional.of(T t) --- 创建一个Optional 实例
        Optional<Employee> of = Optional.of(new Employee());
        Employee employee = of.get();
        System.out.println(employee);

        // Optional.empty() --- 创建一个空实例
        Optional<Object> empty = Optional.empty();
        System.out.println(empty.get());

        // Optional.ofNullable(T t) --- 若 t 不为null，创建 Optional 实例，否则创建空实例
        Optional<Employee> ofNullable = Optional.ofNullable(new Employee());
        System.out.println(ofNullable.get());

        // isPresent() --- 判断是否包含值
        if (ofNullable.isPresent()){
            System.out.println(ofNullable.get());
        }

        // orElse(T t) --- 如果调用对象包含值，返回该值，否则返回t
        Employee emp = ofNullable.orElse(new Employee("东哥哥", 15, 500.0));
        System.out.println(emp);

        // orElseGet(Supplier s) --- 如果调用对象包含值，返回该值，否则返回s获取的值.（这个获取的是一个函数式接口，可以定制功能）
        Employee emp2 = ofNullable.orElseGet(() -> {
            System.out.println("定制功能");
            return new Employee();
        });
        System.out.println(emp2);

        // map(Function f) --- 如果有值对其处理，并返回处理后的Optional，否则返回 Optional.empty
        Optional<String> map = ofNullable.map((e) -> e.getName());
        System.out.println(map.get());

        // flatMap(Function mapper) --- 与 map 类似，要求返回值必须是 Optional
        Optional<String> flatMap = ofNullable.flatMap((e) -> Optional.of(e.getName()));
        System.out.println(flatMap.get());
    }
}
