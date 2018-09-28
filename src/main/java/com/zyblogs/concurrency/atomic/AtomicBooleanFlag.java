package com.zyblogs.concurrency.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Title: AtomicBooleanFlag.java
 * @Package com.zyblogs.concurrency.atomic
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class AtomicBooleanFlag {

    private final static AtomicBoolean flag = new AtomicBoolean(true);

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            while (flag.get()){
                try {
                    Thread.sleep(1_000);
                    System.out.println("I am working.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("I am finished.");
        }).start();

        Thread.sleep(5_000);
        flag.set(false);
    }
}
