package com.zyblogs.concurrency.thread.chapter08;

/**
 * @Title: DeadLockTest.java
 * @Package com.zyblogs.concurrency.thread.chapter08
 * @Description: cmd jps  jstack
 * @Author ZhangYB
 * @Version V1.0
 */
public class DeadLockTest {
    public static void main(String[] args) {
        OtherService otherService = new OtherService();
        DeadLock deadLock = new DeadLock(otherService);
        otherService.setDeadLock(deadLock);

        new Thread(() -> {
            while (true) {
                deadLock.m1();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                otherService.s2();
            }
        }).start();
    }
}
