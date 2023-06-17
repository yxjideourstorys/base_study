package com.study.base.design_pattern.decorator_mode;

public abstract class Decorator implements Component{
    private Component component;

    public Decorator(Component component){
        this.component = component;
    }

    @Override
    public void doSomeThing(){
        // 调用处理业务逻辑
        component.doSomeThing();
    }
}
