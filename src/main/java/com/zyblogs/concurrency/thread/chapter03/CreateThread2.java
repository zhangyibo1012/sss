package com.zyblogs.concurrency.thread.chapter03;

import java.util.Arrays;

/**
 * @Title: CreateThread2.java
 * @Package com.zyblogs.concurrency.thread.chapter03
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class CreateThread2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
//        System.out.println(t1.getThreadGroup());
//        System.out.println(Thread.currentThread().getName());
//        System.out.println(Thread.currentThread().getThreadGroup().getName());

        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();

        // 返回此线程组及其子组中活动线程数的估计值。
        System.out.println(threadGroup.activeCount());

        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);

        // JDK 8
        Arrays.asList(threads).forEach(System.out::println);

//        for (Thread thread : threads) {
//            System.out.println(thread);
//        }
    }
}
