package com.zyblogs.concurrency.pattern.chapter11;

import java.util.stream.IntStream;

/**
 * @Title: ContextTest.java
 * @Package com.zyblogs.concurrency.pattern.chapter11
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ContextTest {
    public static void main(String[] args) {

        IntStream.range(1, 5).forEach(i -> {
            new Thread(new ExecutionTask()).start();
        });
    }
}
