package com.zyblogs.concurrency.pattern.chapter14;

/**
 * @Title: CountDown.java
 * @Package com.zyblogs.concurrency.pattern.chapter14
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class CountDown {

    private final int total;

    private int counter;

    public CountDown(int total) {
        this.total = total;
    }

    public void down() {
        synchronized (this){
            this.counter++;
            this.notifyAll();
        }
    }

    public void await() throws InterruptedException {
        synchronized (this){
            while (counter != total){
                this.wait();
            }
        }
    }
}
