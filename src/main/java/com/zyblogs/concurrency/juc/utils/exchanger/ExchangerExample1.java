package com.zyblogs.concurrency.juc.utils.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @Title: ExchangerExample1.java
 * @Package com.zyblogs.concurrency.juc.utils.exchanger
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ExchangerExample1 {
    /**
     * 数据交换
     * V  r =  exchange(V v)
     * v:代表想传递的数据
     * r:对方给你返回的值
     * NOTE:
     *  超时时间设置: if the pair thread not reached change point. the thread will waiting
     *  必须成对出现
     *
     * @param args
     */
    public static void main(String[] args) {
        final Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start.");
            try {
               String result = exchanger.exchange("I am come from T-A" ,10, TimeUnit.SECONDS );
                System.out.println(Thread.currentThread().getName() + " Get Value [ " + result + " ]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
                System.out.println("Time Out Exception");
            }
            System.out.println(Thread.currentThread().getName() + " end.");
        }, "==A==").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " start.");
            try {
                TimeUnit.SECONDS.sleep(9);
                String result = exchanger.exchange("I am come from T-B");
                System.out.println(Thread.currentThread().getName() + " Get Value [ " + result + " ]");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " end.");
        }, "==B==").start();
    }
}
