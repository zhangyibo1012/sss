package com.zyblogs.concurrency.pattern.chapter08;

/**
 * @Title: Future.java
 * @Package com.zyblogs.concurrency.pattern.chapter08
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public interface Future<T> {

    /**
     *  中断异常
     * @return
     * @throws InterruptedException
     */
    T get() throws InterruptedException;
}
