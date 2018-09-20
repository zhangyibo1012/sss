package com.zyblogs.concurrency.thread.chapter07;

/**
 * @Title: SynchronizedTest.java
 * @Package com.zyblogs.concurrency.thread.chapter07
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class SynchronizedTest {

    /**
     *  以final修饰的变量尽量大写
     */
    private final static  Object LOCK = new Object();

    public static void main(String[] args) {

        Runnable runnable = () ->{
            synchronized (LOCK){
                try {
                    Thread.sleep(200_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);
        Thread t3 = new Thread(runnable);

        t1.start();
        t2.start();
        t3.start();
    }
}
