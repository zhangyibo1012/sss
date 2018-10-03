package com.zyblogs.concurrency.juc.utils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Title: SemaphoreExample1.java
 * @Package com.zyblogs.concurrency.juc.utils.semaphore
 * @Description: TODO 信号量 可以当作一个锁用
 * @Author ZhangYB
 * @Version V1.0
 */
public class SemaphoreExample1 {
    public static void main(String[] args) {

        final SemaporeLock lock = new SemaporeLock();

        for (int i= 0 ; i < 2 ; i ++){

            new Thread(() -> {
                try {
                    System.out.println(Thread.currentThread().getName() + " is running.");
                    lock.lock();
                    System.out.println(Thread.currentThread().getName() + " Get The #SemaporeLock");
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
                System.out.println(Thread.currentThread().getName() + " 释放 #SemaporeLock");
            }).start();
        }
    }

    static class SemaporeLock{

        /**
         *  定义Semaphore必须传入一个许可证数字
         *  同一时间允许最大线程获取许可证的数量
         *  当传入1的时候相当于一个锁
         */
        private final Semaphore semaphore = new Semaphore(1);

       public void lock() throws InterruptedException {
           // 申请一个许可证
           semaphore.acquire();
       }

       public void unlock(){
           semaphore.release();
       }
    }

}
