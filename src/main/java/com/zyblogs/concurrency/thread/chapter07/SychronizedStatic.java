package com.zyblogs.concurrency.thread.chapter07;

/**
 * @Title: SychronizedStatic.java
 * @Package com.zyblogs.concurrency.thread.chapter07
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class SychronizedStatic {

    static {
        synchronized (SychronizedStatic.class) {
            try {
                System.out.println("static " + Thread.currentThread().getName());
                Thread.sleep(10_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void m1() {
        System.out.println("m1 " + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized static void m2() {
        System.out.println("m2 " + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void m3() {
        System.out.println("m3 " + Thread.currentThread().getName());
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
