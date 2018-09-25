package com.zyblogs.concurrency.pattern.chapter18;

/**
 * @Title: FutureResult.java
 * @Package com.zyblogs.concurrency.pattern.chapter18
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class FutureResult implements Result {

    private Result result;
    private boolean ready = false;

    public synchronized void setResult(Result result) {
        this.result = result;
        this.ready = true;
        this.notifyAll();
    }

    /**
     * wait必须需要synchronized
     * @return
     */
    @Override
    public synchronized Object getResultValue() {
        while (!ready) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return this.result.getResultValue();
    }
}
