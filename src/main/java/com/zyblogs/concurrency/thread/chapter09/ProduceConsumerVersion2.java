package com.zyblogs.concurrency.thread.chapter09;

import java.util.stream.Stream;

/**
 * @Title: ProduceConsumerVersion2.java
 * @Package com.zyblogs.concurrency.thread.chapter09
 * @Description: 在多个线程下会有问题
 * @Author ZhangYB
 * @Version V1.0
 */
public class ProduceConsumerVersion2 {

    final private Object LOCK = new Object();
    private int i = 0;
    // 是否生产
    private volatile boolean isProduced = false;

    public static void main(String[] args) {

        ProduceConsumerVersion2 pc = new ProduceConsumerVersion2();

        Stream.of("P1", "P2").forEach(n ->
                // 生产
                new Thread(() -> {
                    while (true) {
                        pc.produce();
                    }
                }).start()
        );


        Stream.of("C1", "C2").forEach(n ->
                // 消费
                new Thread(() -> {
                    while (true) {
                        pc.consume();
                    }
                }).start()
        );
    }

    public void produce() {
        synchronized (LOCK) {
            if (isProduced) {
                // 已经生产
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                i++;
                System.out.println("P->" + i);
                // 通知消费者
                LOCK.notify();
                isProduced = true;
            }
        }
    }

    public void consume() {
        synchronized (LOCK) {
            if (isProduced) {
                // 已经生产 去消费
                System.out.println("C->" + i);
                // 通知生产者再次生产
                LOCK.notify();
                isProduced = false;
            } else {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
