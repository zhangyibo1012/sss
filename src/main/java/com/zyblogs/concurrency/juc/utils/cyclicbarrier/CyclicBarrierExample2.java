package com.zyblogs.concurrency.juc.utils.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @Title: CyclicBarrierExample2.java
 * @Package com.zyblogs.concurrency.juc.utils
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class CyclicBarrierExample2 {
    public static void main(String[] args) throws InterruptedException {
        // 当两个线程都完成任务 awaitNumber就会变成0 当调用reset方法 它又恢复到2了
     final    CyclicBarrier cyclicBarrier = new CyclicBarrier(2);
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
//                TimeUnit.SECONDS.sleep(5);
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }).start();

        // 休息小于线程1
//        TimeUnit.MILLISECONDS.sleep(100);
        // 休息大于所有任务耗时
        TimeUnit.SECONDS.sleep(4);
        System.out.println("cyclicBarrier.getNumberWaiting()" + cyclicBarrier.getNumberWaiting());
        System.out.println("cyclicBarrier.getParties()" + cyclicBarrier.getParties());
        System.out.println("cyclicBarrier.isBroken()" + cyclicBarrier.isBroken());
        TimeUnit.SECONDS.sleep(2);

        // reset == initial == finished
        cyclicBarrier.reset();

        System.out.println("========reset()之后============");
        System.out.println("cyclicBarrier.getNumberWaiting()" + cyclicBarrier.getNumberWaiting());
        System.out.println("cyclicBarrier.getParties()" + cyclicBarrier.getParties());
        System.out.println("cyclicBarrier.isBroken()" + cyclicBarrier.isBroken());


    }
}
