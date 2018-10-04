package com.zyblogs.concurrency.juc.utils.forkjoin;

import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * @Title: ForkJoinRecursiveAction.java
 * @Package com.zyblogs.concurrency.juc.utils.forkjoin
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ForkJoinRecursiveAction {

    /**
     *  最大阀值 1000/200 分成5个任务
     */
    private final static int MAX_THRESHOLD = 3;

    /**
     * 累加器 多个线程操作 使用原子类型
     */
    private final static AtomicInteger  SUM = new AtomicInteger(0);

    /**
     *   RecursiveAction 没有返回值
     */
    private static class CalculateRecusiveAction extends RecursiveAction{
        /**
         *  上限  下限
         */
        private final int start;
        private final int end;

         CalculateRecusiveAction(int start, int end) {
            this.start = start;
            this.end = end;
        }


        @Override
        protected void compute() {
            if (end - start <= MAX_THRESHOLD){
                SUM.addAndGet(IntStream.rangeClosed(start, end).sum());
            }else {
                int middle = (start + end) / 2;
                CalculateRecusiveAction leftAction = new CalculateRecusiveAction(start,middle );
                CalculateRecusiveAction rightAction = new CalculateRecusiveAction(middle + 1,end );
                leftAction.fork();
                rightAction.fork();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        final ForkJoinPool forkJoinPool = new ForkJoinPool();

        forkJoinPool.submit(new CalculateRecusiveAction(0,10 ));

        // 没有返回值 等待结果
        forkJoinPool.awaitTermination(10, TimeUnit.SECONDS);

        Optional.of(SUM).ifPresent(System.out::println);
    }
}
