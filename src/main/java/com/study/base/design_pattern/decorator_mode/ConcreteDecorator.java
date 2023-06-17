package com.study.base.design_pattern.decorator_mode;

/**
 * 装饰者模式
 */
public class ConcreteDecorator extends Decorator {

    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void doSomeThing() {
        System.out.println("业务逻辑功能扩展");

        super.doSomeThing();
    }
}
