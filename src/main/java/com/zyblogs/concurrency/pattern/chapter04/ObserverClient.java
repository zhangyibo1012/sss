package com.zyblogs.concurrency.pattern.chapter04;

/**
 * @Title: ObserverClient.java
 * @Package com.zyblogs.concurrency.pattern.chapter04
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ObserverClient {

    public static void main(String[] args) {

        // 主题
        final Subject subject = new Subject();

        new BinaryObserver(subject);
        new OctalObserver(subject);
        System.out.println("=========================");
        subject.setState(10);

        System.out.println("=========================");
        subject.setState(10);

        System.out.println("=========================");
        subject.setState(15);
    }
}
