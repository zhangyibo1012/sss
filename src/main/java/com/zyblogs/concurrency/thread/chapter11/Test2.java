package com.zyblogs.concurrency.thread.chapter11;

import java.util.Arrays;
import java.util.Optional;

/**
 * @Title: Test2.java
 * @Package com.zyblogs.concurrency.thread.chapter11
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class Test2 {
    public void test() {
        Arrays.asList(Thread.currentThread().getStackTrace()).stream()
                .filter(e -> !e.isNativeMethod())
                .forEach(e -> Optional.of(e.getClassName() + ":" + e.getMethodName() + ":" + e.getLineNumber()).ifPresent(System.out::println));
    }
}
