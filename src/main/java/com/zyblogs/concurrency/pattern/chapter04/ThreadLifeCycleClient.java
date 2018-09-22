package com.zyblogs.concurrency.pattern.chapter04;

import java.util.Arrays;

/**
 * @Title: ThreadLifeCycleClient.java
 * @Package com.zyblogs.concurrency.pattern.chapter04
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ThreadLifeCycleClient {

    public static void main(String[] args) {
        new ThreadLifeCycleObserver().concurrentQuery(Arrays.asList("1", "2"));
    }
}
