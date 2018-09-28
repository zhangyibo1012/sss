package com.zyblogs.concurrency.juc.utils;

import java.util.concurrent.CountDownLatch;

/**
 * @Title: CountDownLatchExample2.java
 * @Package com.zyblogs.concurrency.juc.utils
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class CountDownLatchExample2 {

    public static void main(String[] args) throws InterruptedException {

        // 给它一个线程去做事情
        // 允许一个或多个线程等待直到一系列的操作下其它的thred操作完毕在做自己的事情。
        final CountDownLatch latch = new CountDownLatch(1);

        new Thread(() -> {
            System.out.println("Do some init working");
            try {
                Thread.sleep(1_000);
                //  当计数器减为0就退出await
                latch.await();
                System.out.println("Do other working");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                //  当计数器减为0就退出await
                latch.await();
                System.out.println("release");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();


        new Thread(() -> {
            System.out.println("asyn prepare for some data.");
            try {
                Thread.sleep(2_000);
                System.out.println("data prepare for done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                // 计数递减
                latch.countDown();
            }
        }).start();

        // main线程join
        Thread.currentThread().join();
    }
}
