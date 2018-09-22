package com.zyblogs.concurrency.pattern.chapter10;

/**
 * @Title: ThreadLocalSimpleTest.java
 * @Package com.zyblogs.concurrency.pattern.chapter10
 * @Description: TODO ThreadLocal为变量在每个线程中都创建了一个副本，那么每个线程可以访问自己内部的副本变量。
 * @Author ZhangYB
 * @Version V1.0
 */
public class ThreadLocalSimpleTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal() {
        @Override
        protected String initialValue() {
            return "Alex";
        }
    };

    /**
     * JVM start main thread
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
//        threadLocal.set("Alex");
        Thread.sleep(1_000);
        String value = threadLocal.get();
        System.out.println(value);
    }
}
