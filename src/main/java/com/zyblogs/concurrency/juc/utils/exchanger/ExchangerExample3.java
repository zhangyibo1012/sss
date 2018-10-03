package com.zyblogs.concurrency.juc.utils.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Title: ExchangerExample3.java
 * @Package com.zyblogs.concurrency.juc.utils.exchanger
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ExchangerExample3 {

    public static void main(String[] args) {
        final Exchanger<Integer> exchanger = new Exchanger<>();

        new Thread(() -> {
            AtomicReference<Integer> value = new AtomicReference<>(1);
            try {
              while (true){
                  value.set(exchanger.exchange(value.get()));
                  System.out.println("Thread A has Value: " + value.get());
                  TimeUnit.SECONDS.sleep(3);
              }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            AtomicReference<Integer> value = new AtomicReference<>(2);
            try {
                while (true){
                    value.set(exchanger.exchange(value.get()));
                    System.out.println("Thread B has Value: " + value.get());
                    TimeUnit.SECONDS.sleep(2);
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }).start();
    }
}
