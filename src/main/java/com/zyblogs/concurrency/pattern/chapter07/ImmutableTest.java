package com.zyblogs.concurrency.pattern.chapter07;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

/**
 * @Title: ImmutableTest.java
 * @Package com.zyblogs.concurrency.pattern.chapter07
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ImmutableTest {

    @Getter
    private final int age;
    @Getter
    private final String name;
    private final List<String> list;

    public ImmutableTest(int age, String name, List<String> list) {
        this.age = age;
        this.name = name;
        this.list = list;
    }

    public List<String> getList() {
        // get的时候返回不可变 不可操作
        return Collections.unmodifiableList(list);
    }
}
