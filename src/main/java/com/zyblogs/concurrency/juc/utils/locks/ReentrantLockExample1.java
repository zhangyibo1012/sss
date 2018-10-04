package com.zyblogs.concurrency.juc.utils.locks;

import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Title: ReentrantLockExample1.java
 * @Package com.zyblogs.concurrency.juc.utils.locks
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ReentrantLockExample1 {

    /**
     *  定义一个锁
     */
//    private static final Lock lock = new ReentrantLock();
    private static final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {

        // 定义两个线程
//        IntStream.range(0 ,2 ).forEach(i -> new Thread(() -> needLock()).start());

        Thread thread1 = new Thread(() -> testUnInterruptibly());
        thread1.start();

        TimeUnit.SECONDS.sleep(1);

        Thread thread2 = new Thread(() -> testUnInterruptibly());
        thread2.start();

        TimeUnit.SECONDS.sleep(1);

//        thread2.interrupt();
        System.out.println("============");
        // 等待这个锁的线程数
        Optional.of(lock.getQueueLength()).ifPresent(System.out::println);
        // Queued中是否有这个Thread
        Optional.of(lock.hasQueuedThreads()).ifPresent(System.out::println);
        // thread2 有 true'
        Optional.of(lock.hasQueuedThread(thread2)).ifPresent(System.out::println);
        // false
        Optional.of(lock.hasQueuedThread(thread1)).ifPresent(System.out::println);


//        Thread thread1 = new Thread(() -> testTryLock());
//        thread1.start();
//
//        TimeUnit.SECONDS.sleep(1);
//
//        Thread thread2 = new Thread(() -> testTryLock());
//        thread2.start();
//
//        TimeUnit.SECONDS.sleep(1);


    }

    public static void testTryLock(){

        // 尝试拿锁 如果拿到做事情 如果拿不到 不用block住  退出生命周期 线程堆栈找不到这个线程的信息
         if ( lock.tryLock()){
            try {
                Optional.of("Thr thread - " + Thread.currentThread().getName() + " get lock and will do working" ).ifPresent(System.out::println);
                while (true){

                }
            }finally {
                lock.unlock();
            }
        }else {
            Optional.of("Thr thread - " + Thread.currentThread().getName() + " not get lock " ).ifPresent(System.out::println);
        }
    }


    public static void testUnInterruptibly(){

        try {
            // 允许被打断
            lock.lockInterruptibly();

            // 当前线程保持此锁定的次数，或者如果当前线程未保持此锁定则为零
            Optional.of(Thread.currentThread().getName() +" : " +lock.getHoldCount()).ifPresent(System.out::println);
            Optional.of("Thr thread - " + Thread.currentThread().getName() + " get lock and will do working" ).ifPresent(System.out::println);
            while (true){

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Optional.of("Thr thread - " + Thread.currentThread().getName() + " 释放锁 " ).ifPresent(System.out::println);
            lock.unlock();
        }
    }


    /**
     *  显示锁比synchronized性能要高
     */
    public static void needLock(){
        try {
            // 不可以被打断
            lock.lock();
            Optional.of("Thr thread - " + Thread.currentThread().getName() + " get lock and will do working" ).ifPresent(System.out::println);
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            Optional.of("Thr thread - " + Thread.currentThread().getName() + " 释放锁 " ).ifPresent(System.out::println);
            lock.unlock();
        }
    }

    public static void needLockBySync(){
        synchronized (ReentrantLockExample1.class){
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
