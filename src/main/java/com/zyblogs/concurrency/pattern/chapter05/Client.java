package com.zyblogs.concurrency.pattern.chapter05;

/**
 * @Title: Client.java
 * @Package com.zyblogs.concurrency.pattern.chapter05
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class Client {
    public static void main(String[] args) {
        Gate gate = new Gate();
        User bj = new User("Baobao", "Beijing", gate);
        User sh = new User("ShangLao", "ShangHai", gate);
        User gz = new User("GuangLao", "GuangZhou", gate);

        bj.start();
        sh.start();
        gz.start();
    }
}
