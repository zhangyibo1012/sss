package com.zyblogs.concurrency.juc.utils.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Title: CountDownLatchExample3.java
 * @Package com.zyblogs.concurrency.juc.utils
 * @Description: TODO api
 * @Author ZhangYB
 * @Version V1.0
 */
public class CountDownLatchExample3 {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        final Thread mainThread = Thread.currentThread();

        new Thread(() -> {
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 递减
            latch.countDown();
//            mainThread.interrupt();
        }).start();

        // 当递减为0会退出awit 或中断main线程会退出
//        latch.await();

        // 等待时间1000毫秒 超时就不等待 继续往下执行  如果工作线程小于1000毫秒 工作完成后就退出 不会等待1000ms
        latch.await(1_000,TimeUnit.MILLISECONDS);
        System.out.println("============");

    }
}
