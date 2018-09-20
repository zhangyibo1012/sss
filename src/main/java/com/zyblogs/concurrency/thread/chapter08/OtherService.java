package com.zyblogs.concurrency.thread.chapter08;

import lombok.Setter;

/**
 * @Title: OtherService.java
 * @Package com.zyblogs.concurrency.thread.chapter08
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class OtherService {

    private final Object LOCK = new Object();

    @Setter
    private DeadLock deadLock;

    public void s1() {
        synchronized (LOCK){
            System.out.println("s1=================");
        }
    }


    public void s2() {
        synchronized (LOCK){
            System.out.println("s2=================");
            deadLock.m2();
        }
    }
}
