package com.zyblogs.concurrency.pattern.chapter17;

/**
 * @Title: WorkerClient.java
 * @Package com.zyblogs.concurrency.pattern.chapter17
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class WorkerClient {

    public static void main(String[] args) {
        final Channel channel = new Channel(5);
        channel.startWorker();

        new TransportThread("Alex", channel).start();
        new TransportThread("Jack", channel).start();
        new TransportThread("Willian", channel).start();
    }
}
