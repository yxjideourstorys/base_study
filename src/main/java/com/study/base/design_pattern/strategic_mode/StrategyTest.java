package com.study.base.design_pattern.strategic_mode;

/**
 * 策略模式
 */
public class StrategyTest {

    public static void main(String[] args) {

        int x = 5;
        int y = 2;

        Context context1  = new Context(new Add());
        int add = context1 .execute(x,y);
        System.out.println("x = 24, y = 8, x+y=" + add);

        Context context2  = new Context(new Sub());
        int sub = context2 .execute(x,y);
        System.out.println("x = 24, y = 8, x-y=" + sub);

        Context context3  = new Context(new Mult());
        int mult = context3 .execute(x,y);
        System.out.println("x = 24, y = 8, x*y=" + mult);

    }
}
