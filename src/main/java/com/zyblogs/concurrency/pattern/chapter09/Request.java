package com.zyblogs.concurrency.pattern.chapter09;

import lombok.Getter;

/**
 * @Title: Request.java
 * @Package com.zyblogs.concurrency.pattern.chapter09
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class Request {

    @Getter
    final private String value;

    public Request(String value) {
        this.value = value;
    }
}
