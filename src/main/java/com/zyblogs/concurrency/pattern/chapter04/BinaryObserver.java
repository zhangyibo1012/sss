package com.zyblogs.concurrency.pattern.chapter04;

/**
 * @Title: BinaryObserver.java
 * @Package com.zyblogs.concurrency.pattern.chapter04
 * @Description: 二进制
 * @Author ZhangYB
 * @Version V1.0
 */
public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Binary String:" + Integer.toBinaryString(subject.getState()));
    }
}
