package com.zyblogs.concurrency.juc.utils.exchanger;

import java.util.concurrent.Exchanger;

/**
 * @Title: ExchangerExample2.java
 * @Package com.zyblogs.concurrency.juc.utils.exchanger
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ExchangerExample2 {
    /**
     *  Actor
     *  发送和接收的数据是否一致，地址值指向同一个堆内存
     * @param args
     */
    public static void main(String[] args) {
        final Exchanger<Object> exchanger = new Exchanger<>();

        new Thread(() -> {
            Object aobj = new Object();
            System.out.println("A will send the object " + aobj);
            try {
                // 交换数据
                Object robj = exchanger.exchange(aobj);
                System.out.println("A recieved the object  " + robj);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            Object bobj = new Object();
            System.out.println("B will send the object " + bobj);
            try {
                // 交换数据
                Object robj = exchanger.exchange( bobj);
                System.out.println("B recieved the object  " + robj);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();
    }
}
