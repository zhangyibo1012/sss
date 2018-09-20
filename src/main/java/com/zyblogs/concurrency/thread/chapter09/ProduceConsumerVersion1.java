package com.zyblogs.concurrency.thread.chapter09;

/**
 * @Title: ProduceConsumerVersion1.java
 * @Package com.zyblogs.concurrency.thread.chapter09
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ProduceConsumerVersion1 {

    private int i = 1;

    final private Object LOCK = new Object();

    /**
     *  生产者
     */
    private void produce(){
        synchronized (LOCK){
            System.out.println("P->" + (i ++));
        }
    }

    /**
     * 消费者
     */
    private void consume(){
        System.out.println("C->" + i);
    }

    public static void main(String[] args) {

        ProduceConsumerVersion1 pc = new ProduceConsumerVersion1();

        // 生产者线程
        new Thread("P"){
            @Override
            public void run() {
                while (true) {
                    pc.produce();
                }
            }
        }.start();

        // 消费者线程
        new Thread("C"){
            @Override
            public void run() {
                while (true) {
                    pc.consume();
                }
            }
        }.start();
    }
}
