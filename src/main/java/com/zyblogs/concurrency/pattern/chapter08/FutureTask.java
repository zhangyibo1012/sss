package com.zyblogs.concurrency.pattern.chapter08;

/**
 * @Title: FutureTask.java
 * @Package com.zyblogs.concurrency.pattern.chapter08
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public interface FutureTask<T> {

    T call();
}
