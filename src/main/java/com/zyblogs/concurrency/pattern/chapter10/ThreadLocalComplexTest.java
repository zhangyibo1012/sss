package com.zyblogs.concurrency.pattern.chapter10;

import java.util.Random;

/**
 * @Title: ThreadLocalComplexTest.java
 * @Package com.zyblogs.concurrency.pattern.chapter10
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ThreadLocalComplexTest {

    private final static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    private final static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            threadLocal.set("Thread-T1");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            threadLocal.set("Thread-T2");
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

        System.out.println("========================");

        // main 主线程取值
        System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
    }
}
