package com.zyblogs.concurrency.thread.chapter02;

/**
 * @Title: TaxCalculatorMain.java
 * @Package com.zyblogs.concurrency.thread.chapter02
 * @Description: 税率
 * @Author ZhangYB
 * @Version V1.0
 */
public class TaxCalculatorMain {
    public static void main(String[] args) {
//        TaxCalaculator calaculator = new TaxCalaculator(10000 , 2000){
//            @Override
//            public double calcTax() {
//                return getSalary() * 0.1 + getBouns() * 0.15 ;
//            }
//        };
//        double tax = calaculator.calcuate();
//        System.out.println(tax);

        TaxCalaculator calaculator = new TaxCalaculator(10000d
                , 2000d);
    CalculatorStrategy strategy = new SimpleCalculatorStrategy();
    calaculator.setCalculatorStrategy(strategy);
        System.out.println(calaculator.calcuate());
    }
}
