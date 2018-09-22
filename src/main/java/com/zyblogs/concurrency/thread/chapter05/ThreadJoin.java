package com.zyblogs.concurrency.thread.chapter05;

import java.util.Optional;
import java.util.stream.IntStream;

/**
 * @Title: ThreadJoin.java
 * @Package com.zyblogs.concurrency.thread.chapter05
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ThreadJoin {

    public static void main(String[] args) throws InterruptedException {

        // 打印0到1000的数字
        Thread t1 = new Thread(() -> IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i)));

        Thread t2 = new Thread(() -> IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i)));

        t1.start();
        t2.start();

        // t1 t2 并发执行
        // 相当于main线程 等待t1 t2线程执行结束再去执行main线程
        t1.join();
        t2.join();

        Optional.of("All of tasks finish done.").ifPresent(System.out::println);

        IntStream.range(1, 1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));
    }
}
