package com.zyblogs.concurrency.pattern.chapter15;

import java.util.stream.IntStream;

/**
 * @Title: PerThreadClient.java
 * @Package com.zyblogs.concurrency.pattern.chapter15
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class PerThreadClient {

    public static void main(String[] args) {

        final MessageHandler handler = new MessageHandler();

        IntStream.rangeClosed(0,10).forEach(i -> handler.request(new Message(String.valueOf(i))));

        handler.shutdown();
    }
}
