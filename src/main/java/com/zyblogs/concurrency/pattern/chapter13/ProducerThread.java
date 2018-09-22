package com.zyblogs.concurrency.pattern.chapter13;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title: ProducerThread.java
 * @Package com.zyblogs.concurrency.pattern.chapter13
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ProducerThread  extends Thread{

    private final MessageQueue messageQueue;

    private final Random random = new Random(System.currentTimeMillis());

    /**
     *  原子性的Integer对象 计数器
     */
    private final static AtomicInteger counter = new AtomicInteger(0);

    public ProducerThread(MessageQueue messageQueue , int seq) {
        super("PRODUCER-" + seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Message message = new Message("Message-" + counter.getAndIncrement());
                messageQueue.put(message);
                System.out.println(Thread.currentThread().getName() + " put message " + message.getData());
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
