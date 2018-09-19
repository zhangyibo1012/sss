package com.zyblogs.concurrency.thread.chapter02;

/**
 * @Title: SimpleCalculatorStrategy.java
 * @Package com.zyblogs.concurrency.thread.chapter02
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class SimpleCalculatorStrategy implements CalculatorStrategy {

    private final static double SALARY_RATE = 0.1;
    private final static double BONUS_RATE = 0.15;

    @Override
    public double calculate(double salary, double bonus) {
        return salary * SALARY_RATE + bonus * BONUS_RATE;
    }
}
