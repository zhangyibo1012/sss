package com.zyblogs.concurrency.juc.utils.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;
import java.util.stream.IntStream;

/**
 * @Title: ForkJoinRecursiveTask.java
 * @Package com.zyblogs.concurrency.juc.utils.forkjoin
 * @Description: TODO 分而治之 一个任务分成多个部分 多个线程执行
 * @Author ZhangYB
 * @Version V1.0
 */
public class ForkJoinRecursiveTask {

    /**
     *  最大阀值 1000/200 分成5个任务
     */
    private final static int MAX_THRESHOLD = 200;

    /**
     *  RecursiveTask 有返回值
     */
    private static class CalcultedRecursiveTask extends RecursiveTask<Integer>{

        /**
         *  上限  下限
         */
        private final int start;
        private final int end;

         CalcultedRecursiveTask(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
             // 在MAX_THRESHOLD范围 自己可以处理 就不会进行拆分
             if (end - start <= MAX_THRESHOLD){
                 return IntStream.rangeClosed(start, end).sum();
             }else {
                 // 拆分
                 int middle = (start + end) / 2;
                 CalcultedRecursiveTask leftTask = new CalcultedRecursiveTask(start ,middle);
                 CalcultedRecursiveTask rightTask = new CalcultedRecursiveTask(middle + 1 ,end);

                 // 执行
                 leftTask.fork();
                 rightTask.fork();

                 // 结果
                 return leftTask.join() + rightTask.join();
             }
        }
    }

    public static void main(String[] args) {

        final ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinTask<Integer> future = forkJoinPool.submit(new CalcultedRecursiveTask(0, 1000));
        try {
            Integer result = future.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
