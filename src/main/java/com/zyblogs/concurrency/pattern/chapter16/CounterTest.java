package com.zyblogs.concurrency.pattern.chapter16;

/**
 * @Title: CounterTest.java
 * @Package com.zyblogs.concurrency.pattern.chapter16
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class CounterTest {
    public static void main(String[] args) {

        CounterIncrement counterIncrement = new CounterIncrement();
        counterIncrement.start();

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        counterIncrement.close();
    }
}
