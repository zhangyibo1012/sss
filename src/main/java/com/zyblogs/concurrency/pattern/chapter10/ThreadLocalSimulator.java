package com.zyblogs.concurrency.pattern.chapter10;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: ThreadLocalSimulator.java
 * @Package com.zyblogs.concurrency.pattern.chapter10
 * @Description: TODO 线程局部模拟器 始终已当前线程作为Key值
 * @Author ZhangYB
 * @Date 2018-09-21 18:59
 * @Version V1.0
 */
public class ThreadLocalSimulator<T> {

    private final Map<Thread, T> storage = new HashMap<>();

    public void set(T t) {
        synchronized (this) {
            Thread key = Thread.currentThread();
            storage.put(key, t);
        }
    }


    public T get() {
        synchronized (this) {
            Thread key = Thread.currentThread();
            T value = storage.get(key);
            if (value == null) {
                return initialValue();
            }
            return value;
        }
    }

    public T initialValue() {
        return null;
    }
}