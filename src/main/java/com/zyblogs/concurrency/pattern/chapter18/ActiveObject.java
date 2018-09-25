package com.zyblogs.concurrency.pattern.chapter18;

/**
 * @Title: ActiveObject.java
 * @Package com.zyblogs.concurrency.pattern.chapter18
 * @Description: TODO 接受异步消息的主动方法
 * @Author ZhangYB
 * @Version V1.0
 */
public interface ActiveObject {

    Result makeString(int count ,char fillChar);

    void displayString(String text);
}
