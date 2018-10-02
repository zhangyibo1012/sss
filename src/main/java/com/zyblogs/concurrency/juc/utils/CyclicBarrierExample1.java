package com.zyblogs.concurrency.juc.utils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Title: CyclicBarrierExample1.java
 * @Package com.zyblogs.concurrency.juc.utils
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class CyclicBarrierExample1 {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        // 任务分片 比如完成一件事情,把它分成了2到3部分,只有这几个部分全部做完才代表这个事情完成了 runnable任务都做完了回调
    final  CyclicBarrier cyclicBarrier = new CyclicBarrier(2, () -> System.out.println("all of finished"));

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(20);
                System.out.println("T1 finished");

                cyclicBarrier.await();
                System.out.println("T1 The other thread finished too.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
                System.out.println("T2 finished");
                cyclicBarrier.await();
                System.out.println("T2 The other thread finished too.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        // main线程
//        cyclicBarrier.await();
//        System.out.println("all of finished.");

        while (true){
        System.out.println(cyclicBarrier.getNumberWaiting());
        System.out.println(cyclicBarrier.getParties());
        System.out.println(cyclicBarrier.isBroken());
        TimeUnit.MILLISECONDS.sleep(1);
     }
    }
}
