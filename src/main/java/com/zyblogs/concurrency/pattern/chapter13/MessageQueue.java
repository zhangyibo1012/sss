package com.zyblogs.concurrency.pattern.chapter13;

import java.util.LinkedList;

/**
 * @Title: MessageQueue.java
 * @Package com.zyblogs.concurrency.pattern.chapter13
 * @Description: TODO 生产者消费者
 * @Author ZhangYB
 * @Version V1.0
 */
public class MessageQueue {

    /**
     *  保护共享数据 queue
     */
    private final LinkedList<Message> queue;

    private final static int DEFAULT_MAX_LIMIT = 100;

    private final int limit;

    public MessageQueue() {
        this(DEFAULT_MAX_LIMIT);
    }

    public MessageQueue(int limit) {
        this.limit = limit;
        this.queue = new LinkedList<>();
    }

    public void put(Message message) throws InterruptedException {
        synchronized (queue){
            while (queue.size() > limit){
                queue.wait();
            }

            queue.addLast(message);
            queue.notifyAll();
        }
    }

    public Message take() throws InterruptedException {
        synchronized (queue){
            while (queue.isEmpty()){
                queue.wait();
            }

            Message message = queue.removeFirst();
            queue.notifyAll();
            return message;
        }
    }

    public int getMaxLimit(){
        return this.limit;
    }

    public int getMessageSize(){
        synchronized (queue){
            return queue.size();
        }
    }

}
