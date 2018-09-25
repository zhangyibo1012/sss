package com.zyblogs.concurrency.pattern.chapter18;

import java.util.LinkedList;

/**
 * @Title: ActivationQueue.java
 * @Package com.zyblogs.concurrency.pattern.chapter18
 * @Description: TODO 生产者消费者
 * @Author ZhangYB
 * @Version V1.0
 */
public class ActivationQueue {

    private final static int MAX_METHOD_REQUEST_QUEUE_SIZE = 100;

    private final LinkedList<MethodRequest> methodQueue;

    public ActivationQueue(){
        methodQueue = new LinkedList<>();
    }

    /**
     * 生产者消费者模型的任务队列，一个生产者一次可能放入多个任务，然后用notifyAll通知消费者，但是并非所有被唤醒的消费者都能取到一个任务，那么队列被读空了之后的消费者肯定得继续await。如果你用if来判断，这个消费者第二次被notify的时候就不会再次判断!(ok to proceed)这个条件了，如果这个时候这个消费者又一次没抢到任务，但是代码还是往下执行了，轻则空指针异常，重了干出什么事情来都说不定了。
     *
     *
     * @param request
     */
    public synchronized void  put(MethodRequest request){
        while (methodQueue.size() >= MAX_METHOD_REQUEST_QUEUE_SIZE){
            try {
                // 大于最大size  等待  唤醒
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            this.methodQueue.addLast(request);
            this.notify();
        }
    }

    public synchronized MethodRequest take(){
        while (methodQueue.isEmpty()){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        MethodRequest methodRequest = methodQueue.removeFirst();
        this.notifyAll();
        return methodRequest;
    }
 }
