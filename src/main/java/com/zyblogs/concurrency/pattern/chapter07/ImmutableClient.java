package com.zyblogs.concurrency.pattern.chapter07;

import java.util.stream.IntStream;

/**
 * @Title: ImmutableClient.java
 * @Package com.zyblogs.concurrency.pattern.chapter07
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ImmutableClient {
    public static void main(String[] args) {

        // share data 共享数据
        Person person = new Person("Alex", "GanSu");

        IntStream.rangeClosed(0, 5).forEach(i -> new UsePersonThread(person).start());
    }
}
