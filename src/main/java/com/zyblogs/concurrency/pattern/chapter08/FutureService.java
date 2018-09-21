package com.zyblogs.concurrency.pattern.chapter08;

import java.util.function.Consumer;

/**
 * @Title: FutureService.java
 * @Package com.zyblogs.concurrency.pattern.chapter08
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class FutureService {

    public <T> Future<T> submit(final FutureTask<T> task){
        AsynFuture<T> asynFuture = new AsynFuture<>();
        new Thread(()->{
            T result = task.call();
            // 通知 改变状态
            asynFuture.done(result);
        }).start();
        return asynFuture;
    }

    /**
     *   回调   consumer.accept(result);
     * @param task
     * @param consumer
     * @param <T>
     * @return
     */
    public <T> Future<T> submit(final FutureTask<T> task, final Consumer<T> consumer) {
        AsynFuture<T> asynFuture = new AsynFuture<>();
        new Thread(() -> {
            T result = task.call();
            asynFuture.done(result);
            consumer.accept(result);
        }).start();
        return asynFuture;
    }
}
