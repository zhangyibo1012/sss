package com.zyblogs.concurrency.pattern.chapter07;

import lombok.Getter;
import lombok.ToString;

/**
 * @Title: Person.java
 * @Package com.zyblogs.concurrency.pattern.chapter07
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
@Getter
@ToString
public final class Person {

    private final String name;
    private final String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
