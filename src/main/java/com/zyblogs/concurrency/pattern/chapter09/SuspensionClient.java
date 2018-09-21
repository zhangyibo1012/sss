package com.zyblogs.concurrency.pattern.chapter09;

/**
 * @Title: SuspensionClient.java
 * @Package com.zyblogs.concurrency.pattern.chapter09
 * @Description: 队列
 * @Author ZhangYB
 * @Version V1.0
 */
public class SuspensionClient {
    public static void main(String[] args) throws InterruptedException {

        final RequestQueue queue = new RequestQueue();
        new ClientThread(queue ,"Alex").start();
        ServerThread serverThread = new ServerThread(queue);
        serverThread.start();
//        serverThread.join();

        Thread.sleep(1_000);
        serverThread.close();

    }
}
