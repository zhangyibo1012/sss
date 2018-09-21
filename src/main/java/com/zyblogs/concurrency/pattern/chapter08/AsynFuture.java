package com.zyblogs.concurrency.pattern.chapter08;

/**
 * @Title: AsynFuture.java
 * @Package com.zyblogs.concurrency.pattern.chapter08
 * @Description: 异步实现
 * @Author ZhangYB
 * @Version V1.0
 */
public class AsynFuture<T> implements Future<T> {

    private volatile boolean done = false;

    private T result;

    public void done( T result) {
        synchronized (this){
            this.result = result;
            this.done = true;
            this.notifyAll();
        }
    }

    /**
     * 中断异常
     *
     * @return
     * @throws InterruptedException
     */
    @Override
    public T get() throws InterruptedException {
        synchronized (this){
            while (!done){
                this.wait();
            }
        }
        return result;
    }
}
