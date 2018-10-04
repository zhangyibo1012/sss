package com.zyblogs.concurrency.juc.utils.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Title: ReadWriteLockExample.java
 * @Package com.zyblogs.concurrency.juc.utils.locks
 * @Description: TODO 读写分离锁 写的过程中不能被读  不能同时写 如果线程都是读的时候 就不应该加锁
 * @Author ZhangYB
 * @Version V1.0
 */
public class ReadWriteLockExample {

    /**
     *  true false 是否公平
     */
    private final static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);

    // 获取两把锁
    private final static Lock readLock =  readWriteLock.readLock();
    private final static Lock writeLock =  readWriteLock.writeLock();

    private static final List<Long>  data = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(ReadWriteLockExample::read);
        thread1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread thread2 = new Thread(ReadWriteLockExample::read);
        thread2.start();

    }

    public static void write(){
        try {
            writeLock.lock();
            data.add(System.currentTimeMillis());
            TimeUnit.SECONDS.sleep(5);
            System.out.println(Thread.currentThread().getName() +  " Write=====================");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    public static void read(){
            try {
                readLock.lock();
                data.forEach(System.out::println);
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() +  " Read=====================");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                readLock.unlock();
            }
    }
}
