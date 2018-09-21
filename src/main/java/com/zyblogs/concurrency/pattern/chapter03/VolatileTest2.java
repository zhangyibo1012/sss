package com.zyblogs.concurrency.pattern.chapter03;

/**
 * @Title: VolatileTest2.java
 * @Package com.zyblogs.concurrency.pattern.chapter03
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class VolatileTest2 {

    private static volatile int INIT_VALUE = 0;

    private final static int MAX_LIMIT = 500;

    public static void main(String[] args) {
        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT) {
                int localValue = INIT_VALUE;
                localValue++;
                INIT_VALUE = localValue;
                System.out.println("T1->" + localValue);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ADDER-1").start();

        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT) {
                int localValue = INIT_VALUE;
                localValue++;
                INIT_VALUE = localValue;
                System.out.println("T2->" + localValue);
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "ADDER-1").start();
    }
}
