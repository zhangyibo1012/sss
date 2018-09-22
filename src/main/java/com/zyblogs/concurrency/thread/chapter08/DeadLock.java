package com.zyblogs.concurrency.thread.chapter08;

/**
 * @Title: DeadLock.java
 * @Package com.zyblogs.concurrency.thread.chapter08
 * @Description: 死锁检查
 * @Author ZhangYB
 * @Version V1.0
 */
public class DeadLock {

    private final Object LOCK = new Object();
    private OtherService otherService;

    public DeadLock(OtherService otherService) {
        this.otherService = otherService;
    }

    public void m1() {
        synchronized (LOCK) {
            System.out.println("m1==========");
            otherService.s1();
        }
    }

    public void m2() {
        synchronized (LOCK) {
            System.out.println("m2==========");
            otherService.s2();
        }
    }
}
