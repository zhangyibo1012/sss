package com.zyblogs.concurrency.juc.utils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Title: SemaphoreExample2.java
 * @Package com.zyblogs.concurrency.juc.utils.semaphore
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class SemaphoreExample2 {

    /**
     *  connection pool
     *  When get the not available connection/policy
     *  1.Get 1000MS the throw exception
     *  2.blocking
     *  3.discard
     *  4.Get then throw exception
     *  5.get -> register the callback -> call you.
     *
     * @param args
     */
    public static void main(String[] args) {

        /**
         *  定义Semaphore必须传入一个许可证数字
         *  同一时间允许最大线程获取许可证的数量
         *  当传入1的时候相当于一个锁
         */
        final Semaphore semaphore = new Semaphore(2);

        for (int i = 0 ; i < 3 ; i ++){
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " in");
                try {
                    // 获取许可证信号量
                    semaphore.acquire(1);
                    System.out.println(Thread.currentThread().getName() + " Get The semaphore.");
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    // 释放
                    semaphore.release(1);
                }
                System.out.println(Thread.currentThread().getName() + " out");
            }).start();
        }

        while (true){
            try {
                // 当前可用的许可证
                System.out.println("AP->" + semaphore.availablePermits());
                // 评估的一个值 等待此锁定的估计线程数
                System.out.println("QL->" + semaphore.getQueueLength());
                System.out.println("=============================" );
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
