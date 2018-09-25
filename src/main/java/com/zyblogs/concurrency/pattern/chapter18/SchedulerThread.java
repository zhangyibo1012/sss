package com.zyblogs.concurrency.pattern.chapter18;

/**
 * @Title: SchedulerThread.java
 * @Package com.zyblogs.concurrency.pattern.chapter18
 * @Description: TODO SchedulerThread 任务调度器
 * @Author ZhangYB
 * @Version V1.0
 */
public class SchedulerThread extends Thread{


    private final ActivationQueue activationQueue;

    public SchedulerThread(ActivationQueue activationQueue) {
        this.activationQueue = activationQueue;
    }

    public void invoke(MethodRequest request) {
        this.activationQueue.put(request);
    }

    @Override
    public void run() {
        while (true) {
            activationQueue.take().execute();
        }
    }
}
