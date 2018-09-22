package com.zyblogs.concurrency.thread.chapter10;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @Title: BooleanLock.java
 * @Package com.zyblogs.concurrency.thread.chapter10
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class BooleanLock implements Lock {

    /**
     * true  已经被拿走  false 闲的 可以抢
     */
    private boolean initValue;

    private Collection<Thread> blockedThreadCollection = new ArrayList<>();

    private Thread currentThread;

    public BooleanLock() {
        this.initValue = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (initValue) {
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();
        }

        blockedThreadCollection.remove(Thread.currentThread());
        this.initValue = true;
        currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeOutException {
        if (mills <= 0) {
            lock();
        }

        long hasRemaining = mills;
        long endTime = System.currentTimeMillis() + mills;
        while (initValue) {
            if (hasRemaining <= 0) {
                throw new TimeOutException("Time out");
            }
            blockedThreadCollection.add(Thread.currentThread());
            this.wait(mills);
            hasRemaining = endTime - System.currentTimeMillis();
        }

        this.initValue = true;
        this.currentThread = Thread.currentThread();

    }

    @Override
    public synchronized void unlock() {
        if (Thread.currentThread() == currentThread) {
            this.initValue = false;
            System.out.println(Thread.currentThread() + "release the lock monitor");
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        // 不允许别人修改
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockedThreadCollection.size();
    }
}
