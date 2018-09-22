package com.zyblogs.concurrency.pattern.chapter09;


import java.util.LinkedList;

/**
 * @Title: RequestQueue.java
 * @Package com.zyblogs.concurrency.pattern.chapter09
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class RequestQueue {

    private final LinkedList<Request> queue = new LinkedList<>();

    public Request getRequest() {
        synchronized (queue) {
            while (queue.size() <= 0) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    return null;
                }
            }
            return queue.removeFirst();
        }
    }

    public void putRequest(Request request) {
        synchronized (queue) {
            queue.addLast(request);
            queue.notifyAll();
        }
    }
}
