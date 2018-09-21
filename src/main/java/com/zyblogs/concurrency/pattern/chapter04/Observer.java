package com.zyblogs.concurrency.pattern.chapter04;

/**
 * @Title: Observer.java
 * @Package com.zyblogs.concurrency.pattern.chapter04
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public abstract class Observer {

    protected Subject subject;

    public Observer(Subject subject){
        this.subject = subject;
        this.subject.attch(this);
    }

    public abstract void update();
}
