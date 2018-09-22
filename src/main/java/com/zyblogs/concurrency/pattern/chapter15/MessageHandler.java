package com.zyblogs.concurrency.pattern.chapter15;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Title: MessageHandler.java
 * @Package com.zyblogs.concurrency.pattern.chapter15
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class MessageHandler {

    private final static Random random = new Random(System.currentTimeMillis());

    /**
     *  线程池
     */
    private final static Executor executor = Executors.newFixedThreadPool(5);

    public void request(Message message) {
        executor.execute(() -> {
            String value = message.getValue();
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println("The message will be handle by " + Thread.currentThread().getName() + " " + value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
//        new Thread(()->{
//            String value = message.getValue();
//            try {
//                Thread.sleep(random.nextInt(1_000));
//                System.out.println("The message will be handle by " + Thread.currentThread().getName());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }).start();
    }

    /**
     *  关闭
     */
    public void shutdown() {
        ((ExecutorService) executor).shutdown();
    }
}
