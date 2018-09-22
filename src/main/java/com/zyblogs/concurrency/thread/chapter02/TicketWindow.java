package com.zyblogs.concurrency.thread.chapter02;

/**
 * @Title: TicketWindow.java
 * @Package com.zyblogs.concurrency.thread.chapter02
 * @Description: 售票窗口
 * @Author ZhangYB
 * @Version V1.0
 */
public class TicketWindow extends Thread {

    /**
     * static 生命周期长和类共存
     */
    private static final int MAX = 50;
    private static int index = 1;
    private final String name;

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {

        while (index <= MAX) {
            System.err.println("柜台:" + name + "当前的号码是:" + index++);
        }
    }
}
