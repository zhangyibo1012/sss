package com.zyblogs.concurrency.pattern.chapter18;

/**
 * @Title: MethodRequest.java
 * @Package com.zyblogs.concurrency.pattern.chapter18
 * @Description: TODO 对应ActiveObject的每一个方法
 * @Author ZhangYB
 * @Version V1.0
 */
public abstract class MethodRequest {

    protected final Servant servant;

    protected final FutureResult futureResult;

    public MethodRequest(Servant servant, FutureResult futureResult) {
        this.servant = servant;
        this.futureResult = futureResult;
    }

    public abstract void execute();
}
