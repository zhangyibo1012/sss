package com.zyblogs.concurrency.pattern.chapter05;

import lombok.AllArgsConstructor;

/**
 * @Title: User.java
 * @Package com.zyblogs.concurrency.pattern.chapter05
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
@AllArgsConstructor
public class User extends Thread {

    private final String myName;

    private final String myAddress;

    private final Gate gate;

    @Override
    public void run() {
        System.out.println(myName + " BEGIN");
        while (true) {
            this.gate.pass(myName, myAddress);
        }
    }
}
