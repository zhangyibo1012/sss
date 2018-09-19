package com.zyblogs.concurrency.thread.chapter02;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @Title: TaxCalaculator.java
 * @Package com.zyblogs.concurrency.thread.chapter02
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
@AllArgsConstructor
@Getter
@Setter
public class TaxCalaculator {

    /**
     *  工资
     */
    private final double salary;

    /**
     *  奖金
     */
    private final double bouns;

    private CalculatorStrategy calculatorStrategy;

    public TaxCalaculator(double salary, double bouns) {
        this.salary = salary;
        this.bouns = bouns;
    }

    protected double calcTax() {

        return calculatorStrategy.calculate(salary , bouns);
    }

    public double calcuate() {
        return this.calcTax();
    }
}
