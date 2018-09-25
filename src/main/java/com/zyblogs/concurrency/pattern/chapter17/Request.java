package com.zyblogs.concurrency.pattern.chapter17;

import lombok.ToString;

/**
 * @Title: Request.java
 * @Package com.zyblogs.concurrency.pattern.chapter17
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class Request {

    private final String name;
    private final int number;

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    /**
     * 执行
     */
    public void execute(){
        System.out.println(Thread.currentThread().getName() + " executed " + this);
    }

    @Override
    public String toString() {
        return "Request = > No. " + number + " Name. " + name;
    }
}
