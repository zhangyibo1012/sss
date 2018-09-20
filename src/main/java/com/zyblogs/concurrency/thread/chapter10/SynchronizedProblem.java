package com.zyblogs.concurrency.thread.chapter10;

import org.omg.PortableServer.THREAD_POLICY_ID;

/**
 * @Title: SynchronizedProblem.java
 * @Package com.zyblogs.concurrency.thread.chapter10
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class SynchronizedProblem {

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            run();
        }).start();

         Thread.sleep(1000);

         Thread t2 = new Thread(() -> {
             run();
         });
         t2.start();
         Thread.sleep(2_000);
         t2.interrupt();
        System.out.println(t2.isInterrupted());
    }

    private synchronized static void run(){
        System.out.println(Thread.currentThread());
        while (true){

        }
    }
}
