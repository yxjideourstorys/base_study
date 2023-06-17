package com.study.base.design_pattern.strategic_mode;

public class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public int execute(int x, int y) {
        return strategy.operate(x, y);
    }
}
