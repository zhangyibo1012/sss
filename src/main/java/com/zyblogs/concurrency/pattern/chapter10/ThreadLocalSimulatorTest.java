package com.zyblogs.concurrency.pattern.chapter10;

import java.util.Random;

/**
 * @Title: ThreadLocalSimulatorTest.java
 * @Package com.zyblogs.concurrency.pattern.chapter10
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ThreadLocalSimulatorTest {

    private final static ThreadLocalSimulator<String> threadLocal = new ThreadLocalSimulator<String>() {
        @Override
        public String initialValue() {
            return "No Value";
        }
    };


    private final static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            threadLocal.set("Thread-T3");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            threadLocal.set("Thread-T4");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("============");
        System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
    }
}
