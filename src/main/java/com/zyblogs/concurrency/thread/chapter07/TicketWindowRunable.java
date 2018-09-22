package com.zyblogs.concurrency.thread.chapter07;

/**
 * @Title: TicketWindowRunable.java
 * @Package com.zyblogs.concurrency.thread.chapter02
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class TicketWindowRunable implements Runnable {

    private final static int MAX = 500;
    /**
     * 同步代码块
     */
    private final Object MONITOR = new Object();
    private int index = 1;

    @Override
    public void run() {
        while (true) {
            // synchronized代码块是单线程执行 会影响效率
            synchronized (MONITOR) {
                if (index > MAX) {
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
}
