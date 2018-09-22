package com.zyblogs.concurrency.pattern.chapter13;

/**
 * @Title: ProducerAndConsumerClient.java
 * @Package com.zyblogs.concurrency.pattern.chapter13
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ProducerAndConsumerClient {

    public static void main(String[] args) {

        final MessageQueue messageQueue = new MessageQueue();
        new ProducerThread(messageQueue, 1).start();
        new ProducerThread(messageQueue, 2).start();
        new ProducerThread(messageQueue, 3).start();
        new ConsumerThread(messageQueue, 1).start();
        new ConsumerThread(messageQueue, 2).start();
    }
}
