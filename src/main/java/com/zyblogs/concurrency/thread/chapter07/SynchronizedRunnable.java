package com.zyblogs.concurrency.thread.chapter07;

/**
 * @Title: SynchronizedRunnable.java
 * @Package com.zyblogs.concurrency.thread.chapter07
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class SynchronizedRunnable implements Runnable{
    private int index = 1;

    private final static int MAX = 500;

    /**
     *  同步代码块
     */
    private final Object MONITOR = new Object();

    @Override
    public synchronized void run() {
        while (true){
            // synchronized代码块是单线程执行 会影响效率
                if (index > MAX){
                    break;
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "的号码是:" + (index++));
            }
            // synchronized代码块是单线程执行
    }
}
