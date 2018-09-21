package com.zyblogs.concurrency.pattern.chapter04;

/**
 * @Title: LifecycleListener.java
 * @Package com.zyblogs.concurrency.pattern.chapter04
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public interface LifecycleListener {

    void onEvent(ObservableRunnable.RunnableEvent event);
}
