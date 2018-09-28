package com.zyblogs.concurrency.atomic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @Title: AtomicReferenceTest.java
 * @Package com.zyblogs.concurrency.atomic
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class AtomicReferenceTest {

    public static void main(String[] args) {

        // Simple对象具备了原子性
        AtomicReference<Simple> atomic = new AtomicReference<>(new Simple("Alex", 20));
        System.out.println( atomic.get());

        boolean result = atomic.compareAndSet(new Simple("sdfs", 22), new Simple("sdfs", 234));

        System.out.println(result);

    }

    @AllArgsConstructor
    @Getter
    @Setter
    static class Simple{
        private String name;
        private int age;
    }
}
