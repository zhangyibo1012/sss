package com.zyblogs.concurrency.pattern.chapter16;

import java.util.Random;

/**
 * @Title: Counter.java
 * @Package com.zyblogs.concurrency.pattern.chapter16
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class CounterIncrement extends Thread{

    private volatile boolean terminated = false;

    private int counter = 0;

    private Random random = new Random(System.currentTimeMillis());

    @Override
    public void run() {

        try {
            while (! terminated) {
                System.out.println(Thread.currentThread().getName() + " " + counter++);
                Thread.sleep(random.nextInt(1_000));
            }
        } catch (InterruptedException e) {
            }finally {

            this.clean();
            }
        }


    /**
     *  中断后操作这句话
     */
    private void clean() {
        System.out.println("do some clean work for the second phase.current counter : " + counter);
    }

    public void close(){
        this.terminated = true;
        // 中断
        this.interrupt();
    }
}
