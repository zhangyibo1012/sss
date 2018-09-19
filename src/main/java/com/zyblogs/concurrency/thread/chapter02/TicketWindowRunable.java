package com.zyblogs.concurrency.thread.chapter02;

/**
 * @Title: TicketWindowRunable.java
 * @Package com.zyblogs.concurrency.thread.chapter02
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class TicketWindowRunable implements Runnable {

    private int index = 1;

    private final static int MAX = 50;

    @Override
    public void run() {
        while (index <= MAX) {
            System.out.println(Thread.currentThread() + " 的号码是:" + (index++));
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
