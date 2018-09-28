package com.zyblogs.concurrency.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Title: AtomicLongFieldUpdaterTest.java
 * @Package com.zyblogs.concurrency.atomic
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class AtomicIntegerFieldUpdaterTest {

    public static void main(String[] args) {
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class, "i");

        TestMe me = new TestMe();

        for (int i = 0; i < 2 ; i ++){
            new Thread(() -> {
                final int MAX = 20;
                for (int j = 0; j < MAX ; j ++ ){
                    // 返回的是加过以后的值
                    int v = updater.incrementAndGet(me);
                    System.out.println(Thread.currentThread().getName() + "=> " + v);
                }
            }).start();
        }
    }

    static class TestMe{

        /**
         *  让TestMe中的i多了一个原子性
         */
        volatile int i;
    }
}
