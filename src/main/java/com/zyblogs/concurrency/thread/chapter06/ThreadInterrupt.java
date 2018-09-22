package com.zyblogs.concurrency.thread.chapter06;

/**
 * @Title: ThreadInterrupt.java
 * @Package com.zyblogs.concurrency.thread.chapter06
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ThreadInterrupt {
    public static void main(String[] args) throws InterruptedException {

        Thread t = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException e) {
                    System.out.println("收到打断信号");
                    e.printStackTrace();
                }
            }
        });

        t.start();
        Thread.sleep(100);
        System.out.println(t.isInterrupted());

        // 中断
        t.interrupt();
        System.out.println(t.isInterrupted());
    }
}
