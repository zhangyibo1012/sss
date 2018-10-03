package com.zyblogs.concurrency.juc.utils.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @Title: SemaphoreExample3.java
 * @Package com.zyblogs.concurrency.juc.utils.semaphore
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class SemaphoreExample3 {

    /**
     *  public void acquire(int permits)
     *  public void acquire()
     *  public void release(int permits)
     *  public void release()
     *  public int availablePermits()
     *  public final int getQueueLength()
     *  public void acquireUninterruptibly()
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        final Semaphore semaphore = new Semaphore(1);

        Thread t1 = new Thread(() -> {
            try {
                semaphore.acquire();
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                semaphore.release();
            }
            System.out.println("T1 finished.");
        });
        t1.start();

        TimeUnit.MILLISECONDS.sleep(50);

        Thread t2 = new Thread(() -> {
            try {
//                    semaphore.acquire();
                // 可以不被中断
                semaphore.acquireUninterruptibly();
//                    TimeUnit.SECONDS.sleep(5);
            } finally {
                semaphore.release();
            }
            System.out.println("T2 finished.");
        });
        t2.start();

        TimeUnit.MILLISECONDS.sleep(50);

        t2.interrupt();
    }
}
