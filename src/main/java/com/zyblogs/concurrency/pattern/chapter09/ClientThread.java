package com.zyblogs.concurrency.pattern.chapter09;

import java.util.Random;

/**
 * @Title: ClientThread.java
 * @Package com.zyblogs.concurrency.pattern.chapter09
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ClientThread extends Thread {

    private final RequestQueue queue;
    private final Random random;
    private final String sendValue;

    public ClientThread(RequestQueue queue, String sendValue) {
        this.queue = queue;
        this.sendValue = sendValue;
        this.random = new Random(System.currentTimeMillis());
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Client -> request " + sendValue);
            queue.putRequest(new Request(sendValue));
            try {
                Thread.sleep(random.nextInt(1_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
