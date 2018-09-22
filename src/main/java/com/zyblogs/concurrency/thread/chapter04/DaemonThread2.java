package com.zyblogs.concurrency.thread.chapter04;

/**
 * @Title: DaemonThread2.java
 * @Package com.zyblogs.concurrency.thread.chapter04
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class DaemonThread2 {

    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            Thread innerThread = new Thread(() -> {
                try {
                    while (true) {
                        System.out.println("Do some thing for health check");
                        Thread.sleep(1_000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });

            // 必须在start之前设置才生效 之后设置会报异常
            innerThread.setDaemon(true);
            innerThread.start();

            try {
                Thread.sleep(1_000);
                System.out.println("T thread finish done.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

//        t.setDaemon(true);
        t.start();
    }
}
