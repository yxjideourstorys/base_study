package com.study.base.design_pattern.strategic_mode;

public class Sub implements Strategy {
    @Override
    public int operate(int x, int y) {
        return x - y;
    }
}
