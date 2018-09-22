package com.zyblogs.concurrency.thread.chapter07;

/**
 * @Title: SynchronizedThis.java
 * @Package com.zyblogs.concurrency.thread.chapter07
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class SynchronizedThis {

    public static void main(String[] args) {

        ThisLock thisLock = new ThisLock();
        new Thread("T1") {
            @Override
            public void run() {
                thisLock.m1();
            }
        }.start();

        new Thread("T2") {
            @Override
            public void run() {
                thisLock.m2();
            }
        }.start();
    }
}

class ThisLock {

    private final Object LOCK = new Object();

    public synchronized void m1() {
        try {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void m2() {
        synchronized (LOCK) {
            try {
                System.out.println(Thread.currentThread().getName());
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
