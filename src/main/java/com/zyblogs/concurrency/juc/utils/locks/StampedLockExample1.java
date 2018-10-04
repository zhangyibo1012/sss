package com.zyblogs.concurrency.juc.utils.locks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;

/**
 * @Title: StampedLockExample.java
 * @Package com.zyblogs.concurrency.juc.utils.locks
 * @Description: TODO 悲观
 * @Author ZhangYB
 * @Version V1.0
 */
public class StampedLockExample1 {

    /**
     *  ReentrantLock VS Synchronized
     *
     *  ReentrantReadWriteLock
     *  100 threads
     *  99 threads need read lock
     *  1  threads need write lock
     *
     * @param args
     */

    private static final StampedLock lock = new StampedLock();

    private final static List<Long> DATA = new ArrayList<>();

    public static void main(String[] args) {

        // 线程池
        ExecutorService executor = Executors.newFixedThreadPool(10);

        Runnable readTask = () ->{
          while (true){
              read();
          }
        };

        Runnable writeTask = () ->{
            while (true){
                write();
            }
        };

        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(readTask);
        executor.submit(writeTask);
    }

    private static void read(){
        long stamped = -1;
        try {
            stamped = lock.readLock();
            String result = DATA.stream().map(String::valueOf).collect(Collectors.joining("#", "R-", ""));
            Optional.of(result).ifPresent(System.out::println);
        Thread.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockRead(stamped);
        }
    }

    private static void write(){
        long stamped = -1;
        try {
            stamped = lock.writeLock();
            DATA.add(System.currentTimeMillis());
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlockWrite(stamped);
        }
    }
}
