package com.zyblogs.concurrency.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title: CompareAndSetLock.java
 * @Package com.zyblogs.concurrency.atomic
 * @Description: TODO 抢不到锁快速失败
 * @Author ZhangYB
 * @Version V1.0
 */
public class CompareAndSetLock {

    private final AtomicInteger value = new AtomicInteger(0);

    /**
     *  那个线程拿到的锁 就由那个线程释放 别的线程不可以释放
     */
    private Thread lockThread;

    public void tryLock() throws GetLockException{
        // 第一个参数等于value 返回true 更新为1
        boolean success = value.compareAndSet(0, 1);
        if (!success){
            throw new GetLockException("Get the Lock filed");
        } else {
            // 当前线程给lockThread
            lockThread = Thread.currentThread();
        }
    }

    public void unlock(){
        if (0 == value.get()){
            return;
        }
        // 1  ->  0
        if (lockThread == Thread.currentThread()) {
            value.compareAndSet(1, 0);
        }
    }
}
