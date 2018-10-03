package com.zyblogs.concurrency.juc.utils.semaphore;

import java.util.Collection;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Title: SemaphoreExample4.java
 * @Package com.zyblogs.concurrency.juc.utils.semaphore
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class SemaphoreExample4 {
    public static void main(String[] args) throws InterruptedException {
        final MySemaphore semaphore = new MySemaphore(5);

        Thread t1 = new Thread(() -> {
            try {
                //获取所有的许可证
                semaphore.drainPermits();
                System.out.println("当前可用的许可证: " + semaphore.availablePermits());
                TimeUnit.SECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release(5);
            }
            System.out.println("T1 finished.");
        });
        t1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread t2 = new Thread(() -> {
            try {
//                semaphore.acquire();
                // 有可能拿不到 不抛中断异常 告诉你是否成功
                boolean b = semaphore.tryAcquire(1 ,TimeUnit.SECONDS);
                System.out.println(b?"Ok" :"Fail");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
            System.out.println("T2 finished.");
        });
        t2.start();

        TimeUnit.SECONDS.sleep(1);

        // 是否有线程等待
        System.out.println( semaphore.hasQueuedThreads());
        Collection<Thread> waitingThreads = semaphore.getWaitingThreads();
        for (Thread t : waitingThreads){
            System.out.println(t);
        }

    }

    static class MySemaphore extends Semaphore{
        public MySemaphore(int permits) {
            super(permits);
        }

        public MySemaphore(int permits, boolean fair) {
            super(permits, fair);

            super.getQueuedThreads();
        }

        public Collection<Thread> getWaitingThreads(){
            return getQueuedThreads();
        }
    }
}
