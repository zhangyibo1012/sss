package com.zyblogs.concurrency.classloader.chapter01;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Title: ClinitThreadTest.java
 * @Package com.zyblogs.concurrency.classloader.chapter01
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ClinitThreadTest {
    public static void main(String[] args) {

        new Thread(()->new SimpleObject()).start();

        new Thread(()->new SimpleObject()).start();
    }

    static class SimpleObject{

        private static AtomicBoolean init = new AtomicBoolean(true);

        static {
            System.out.println(Thread.currentThread().getName()+ " SimpleObject.static will initializer");
            while (init.get()){

            }
            System.out.println(Thread.currentThread().getName()+ " SimpleObject.static finished initializer");
        }
    }
}
