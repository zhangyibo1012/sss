package com.zyblogs.concurrency.juc.utils.cyclicbarrier;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Title: CyclicBarrierExample3.java
 * @Package com.zyblogs.concurrency.juc.utils
 * @Description: TODO  CountDownLatch VS CyclicBarrier
 *                    1. CountDownLatch不能reset，而CyclicBarrier可以reset，重复使用
 *                    2.CountDownLatch工作线程之间互不关心，而CyclicBarrier工作线程必须等到共同的同一个点才去执行某个动作。
 * @Author ZhangYB
 * @Version V1.0
 */
public class CyclicBarrierExample3 {

    static class MyCountDownLatch extends CountDownLatch{
        private final Runnable runnable;
        public MyCountDownLatch(int count, Runnable runnable) {
            super(count);
            this.runnable = runnable;
        }
        @Override
        public void countDown() {
            super.countDown();
            if (getCount() == 0){
                this.runnable.run();
            }
        }
    }

    public static void main(String[] args) {

        final MyCountDownLatch myCountDownLatch = new MyCountDownLatch(2, () -> {
            System.out.println("all of work finished done.");
        });

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myCountDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " finished work");
        }).start();



        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myCountDownLatch.countDown();
            System.out.println(Thread.currentThread().getName() + " finished work");
        }).start();

    }
}
