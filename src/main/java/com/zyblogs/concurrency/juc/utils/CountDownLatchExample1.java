package com.zyblogs.concurrency.juc.utils;

import java.util.Random;
import java.util.concurrent.*;

/**
 * @Title: CountDownLatchExample1.java
 * @Package com.zyblogs.concurrency.juc.utils
 * @Description: TODO CountDownLatch 在串行化的过程当中发现中间或某一部分是可并行化的，那么就选择并行化的方式去做 提高处理速度和效率 然后最终又把它串行化
 * @Author ZhangYB
 * @Version V1.0
 */
public class CountDownLatchExample1 {

    private static Random random = new Random(System.currentTimeMillis());

    /**
     *  同时并发只可以执行2个
     */
    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    private static final CountDownLatch latch = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {
        // (1)
        int[] data = query();
        // (2)
        for (int i = 0 ; i < data.length ; i ++){
            executor.execute(new SimpleRunnable(data, i ,latch));
        }
        // (3)
        // 当计数器减为0就await
        latch.await();
        System.out.println("all of work finish dine.");
        //所有的任务执行完成才showdown
//        executor.shutdown();
        // 等待1小时结束  不到1小时工作结束就不等待 然后执行all of work finish dine
//        executor.awaitTermination(1, TimeUnit.HOURS);
//        System.out.println("all of work finish dine.");

    }

    static class SimpleRunnable implements Runnable{

        private final int[] data;
        private final int index;
        private final CountDownLatch latch;

        public SimpleRunnable(int[] data, int index, CountDownLatch latch) {
            this.data = data;
            this.index = index;
            this.latch = latch;
        }


        @Override
        public void run() {
            try {
//                TimeUnit.SECONDS.sleep(random.nextInt(2_000));
                Thread.sleep(2_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int value = data[index];
            if (value % 2 == 0){
//                <<      :     左移运算符，num << 1,相当于num乘以2
//                >>      :     右移运算符，num >> 1,相当于num除以2
//                >>>     :     无符号右移，忽略符号位，空位都以0补齐
                data[index] = value << 1;
            }else {
                data[index] = value * 10 ;
            }

            System.out.println(Thread.currentThread().getName() + " finished.");
            // 计数减少
            latch.countDown();
        }
    }

    private static int[] query(){
        return new int[]{1,2,3,4,5,6,7,8,9,10};
    }
}
