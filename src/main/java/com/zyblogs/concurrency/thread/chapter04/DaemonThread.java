package com.zyblogs.concurrency.thread.chapter04;

/**
 * @Title: DeamonThread.java
 * @Package com.zyblogs.concurrency.thread.chapter04
 * @Description: 守护线程
 * @Author ZhangYB
 * @Version V1.0
 */
public class DaemonThread {

    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + "running");
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "done.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });  // new

        // 只要当前JVM实例中尚存在任何一个非守护线程没有结束，守护线程就全部工作；只有当最后一个非守护线程结束时，守护线程随着JVM一同结束工作。
        t.setDaemon(true);

        // runnable 可执行状态  -> running | dead | blocked
        t.start();

        try {
            // Jdk 1.7
            Thread.sleep(1_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
