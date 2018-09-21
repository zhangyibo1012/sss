package com.zyblogs.concurrency.pattern.chapter04;

/**
 * @Title: OctalObserver.java
 * @Package com.zyblogs.concurrency.pattern.chapter04
 * @Description: 八进制
 * @Author ZhangYB
 * @Version V1.0
 */
public class OctalObserver extends Observer {


    public OctalObserver(Subject subject) {
        super(subject);
    }

    @Override
    public void update() {
        System.out.println("Octal String:" + Integer.toOctalString(subject.getState()));
    }
}
