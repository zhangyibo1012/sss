package com.zyblogs.concurrency.thread.chapter09;

import java.util.stream.Stream;

/**
 * @Title: ProduceConsumerVersion3.java
 * @Package com.zyblogs.concurrency.thread.chapter09
 * @Description: 多线程下的生产者和消费者
 * @Author ZhangYB
 * @Version V1.0
 */
public class ProduceConsumerVersion3 {

    final private Object LOCK = new Object();
    private int i = 0;
    private volatile boolean isProduced = false;

    public static void main(String[] args) {
        ProduceConsumerVersion3 pc = new ProduceConsumerVersion3();
        Stream.of("P1", "P2", "P3").forEach(n ->
                new Thread(n) {
                    @Override
                    public void run() {
                        while (true) {
                            pc.produce();
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start()
        );
        Stream.of("C1", "C2", "C3", "C4").forEach(n ->
                new Thread(n) {
                    @Override
                    public void run() {
                        while (true) {
                            pc.consume();
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start()
        );
    }

    public void produce() {
        synchronized (LOCK) {
            while (isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            i++;
            System.out.println("P->" + i);
            LOCK.notifyAll();
            isProduced = true;

        }
    }

    public void consume() {
        synchronized (LOCK) {
            while (!isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            // 消费
            System.out.println("C->" + i);
            LOCK.notifyAll();
            isProduced = false;
        }
    }
}