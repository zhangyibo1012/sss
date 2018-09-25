package com.zyblogs.concurrency.pattern.chapter17;

import java.util.Random;

/**
 * @Title: WorkerThread.java
 * @Package com.zyblogs.concurrency.pattern.chapter17
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class WorkerThread extends Thread{

    private final  Channel channel;

    private static final Random random = new Random(System.currentTimeMillis());

    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        while (true){
            // 从队列取出来执行
            channel.take().execute();

            try {
                Thread.sleep(random.nextInt(1_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
