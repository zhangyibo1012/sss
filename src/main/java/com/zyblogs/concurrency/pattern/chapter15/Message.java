package com.zyblogs.concurrency.pattern.chapter15;

import lombok.Getter;

/**
 * @Title: Message.java
 * @Package com.zyblogs.concurrency.pattern.chapter15
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
@Getter
public class Message {

    private final String value;

    public Message(String value) {
        this.value = value;
    }
}
