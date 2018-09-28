package com.zyblogs.concurrency.atomic;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: UnsafeTest.java
 * @Package com.zyblogs.concurrency.atomic
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class UnsafeTest {

    public static void main(String[] args) throws InterruptedException {

        // java.lang.SecurityException
//        Unsafe unsafe = Unsafe.getUnsafe();
//        System.out.println(unsafe);
//        System.out.println(getUnsafe());

        // 定义一个线程池
        ExecutorService service = Executors.newFixedThreadPool(1000);

//        Counter counter = new StupiedCounter();

        // 1173
//        Counter counter = new SyncCounter();

        // Lock 420
        Counter counter = new LockCounter();
        long start = System.currentTimeMillis();
        for (int i = 0 ; i < 1000; i ++){
            // 线程池提交一个任务
            service.submit(new CounterRunnable(counter, 10_000));
        }
        // 异步  shotdown后程序还会继续往下走
        service.shutdown();

        // 等待1g小时
        service.awaitTermination(1, TimeUnit.HOURS);
        long end = System.currentTimeMillis();

        System.out.println("Counter result: " + counter.getCounter());
        System.out.println("Time passed in ms: " + (end -start));
    }

    private static Unsafe getUnsafe()  {
        Field f = null;
        try {
            f = Unsafe.class.getDeclaredField("theUnsafe");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        f.setAccessible(true);
        try {
            return (Unsafe)f.get(null);
        } catch (IllegalAccessException e) {
        throw new RuntimeException();
        }
    }

    static class StupiedCounter implements Counter{

        private long counter = 0;

        @Override
        public void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }



    static class SyncCounter implements Counter{

        private long counter = 0;

        @Override
        public  synchronized  void increment() {
            counter++;
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    static class LockCounter implements Counter{

        private long counter = 0;

        /**
         *  默认不公平new ReentrantLock()
         */
        private final Lock lock = new ReentrantLock();

        @Override
        public    void increment() {
           try {
               lock.lock();
               counter++;
           }finally {
               lock.unlock();
           }
        }

        @Override
        public long getCounter() {
            return counter;
        }
    }

    interface Counter{
        void increment();

        long getCounter();
    }

    static class CounterRunnable implements Runnable{
        private final Counter counter;
        private final int num;

        public CounterRunnable(Counter counter, int num) {
            this.counter = counter;
            this.num = num;
        }

        @Override
        public void run() {
            for (int i= 0; i < num;i ++){
                counter.increment();
            }
        }
    }
}
