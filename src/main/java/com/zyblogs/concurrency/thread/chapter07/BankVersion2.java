package com.zyblogs.concurrency.thread.chapter07;

/**
 * @Title: BankVersion2.java
 * @Package com.zyblogs.concurrency.thread.chapter02
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class BankVersion2 {
    public static void main(String[] args) {


        final TicketWindowRunable ticketWindow = new TicketWindowRunable();

        Thread windowThread1 = new Thread(ticketWindow, "一号窗口");
        Thread windowThread2 = new Thread(ticketWindow, "二号窗口");
        Thread windowThread3 = new Thread(ticketWindow, "三号窗口");
        windowThread1.start();
        windowThread2.start();
        windowThread3.start();
    }
}
