package com.zyblogs.concurrency.pattern.chapter17;

import java.util.Random;

/**
 * @Title: TransportThread.java
 * @Package com.zyblogs.concurrency.pattern.chapter17
 * @Description: TODO 运输线程
 * @Author ZhangYB
 * @Version V1.0
 */
public class TransportThread extends Thread{

    private final Channel channel;

    /**
     *  static 只初始化一次  random只有一个
     */
    private static final Random random = new Random(System.currentTimeMillis());

    public TransportThread(String name ,Channel channel) {
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            for (int i = 0 ; true ; i ++){
                 Request request = new Request(getName() , i);
                 this.channel.put(request);
                 Thread.sleep(random.nextInt(1_000));
            }
        }catch (Exception ex){

        }
    }
}
