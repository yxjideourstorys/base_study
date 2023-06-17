package com.study.base.design_pattern.decorator_mode;

/**
 * 具体构件角色
 */
public class ConcreteComponent implements Component {
    @Override
    public void doSomeThing() {
        System.out.println("处理业务逻辑");
    }
}
