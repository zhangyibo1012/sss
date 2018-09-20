package com.zyblogs.concurrency.thread.chapter05;

/**
 * @Title: ThreadJoin2.java
 * @Package com.zyblogs.concurrency.thread.chapter05
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ThreadJoin2 {
    public static void main(String[] args) throws InterruptedException {
//        Thread t1 = new Thread(()->{
//            try {
//                System.out.println("t1 is running.");
//                Thread.sleep(10_000);
//                System.out.println("t1 is done.");
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        t1.start();
//        t1.join(100);
//
//        IntStream.range(1 ,1000).forEach(i -> System.out.println(Thread.currentThread().getName() + "->" + i));

        // 等待当前线程结束 main
//        Thread.currentThread().join();

        Thread t1 = new Thread(() -> {

            System.out.println("t1 is running");
            while (true) {
                System.out.println(Thread.currentThread().isInterrupted());
            }
        });
        t1.start();
        Thread t2 = new Thread(() -> {
            try {
                Thread.sleep(10000);

                // 中断当前线程
                t1.interrupt();
                System.out.println("interrupt");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        t2.start();

        try {
            t1.join(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
