package com.zyblogs.concurrency.thread.chapter04;

import java.util.Optional;

/**
 * @Title: ThreadSimpleAPI.java
 * @Package com.zyblogs.concurrency.thread.chapter04
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ThreadSimpleAPI {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            Optional.of("Hello").ifPresent(System.out::println);
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "t1");

        t.start();
        // name
        Optional.of(t.getName()).ifPresent(System.out::println);

        // id
        Optional.of(t.getId()).ifPresent(System.out::println);

        // 默认优先级5
        Optional.of(t.getPriority()).ifPresent(System.out::println);
    }
}
