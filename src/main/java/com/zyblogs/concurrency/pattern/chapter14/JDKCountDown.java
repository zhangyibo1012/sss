package com.zyblogs.concurrency.pattern.chapter14;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

/**
 * @Title: JDKCountDown.java
 * @Package com.zyblogs.concurrency.pattern.chapter14
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class JDKCountDown {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        final CountDownLatch latch = new CountDownLatch(5);

        System.out.println("准备多线程处理任务");
        // the first phase

        // 开启5个线程
        IntStream.rangeClosed(1 , 5).forEach(i -> new Thread(()->{
            System.out.println(Thread.currentThread().getName() + " is working");
            try {
                Thread.sleep(random.nextInt(1_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 结束
            latch.countDown();
        },String.valueOf(i)).start());

        // 等着所有线程结束
        latch.await();

        // The second phase.
        System.out.println("多线程任务全部结束,准备第二阶段任务");
        System.out.println("......................");
        System.out.println("FINISH");
    }
}
