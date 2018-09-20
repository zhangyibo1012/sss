package com.zyblogs.concurrency.thread.chapter07;

/**
 * @Title: SychronizedStaticTest.java
 * @Package com.zyblogs.concurrency.thread.chapter07
 * @Description: TODO
 * @Author ZhangYB
 * @Date 2018-09-20 11:35
 * @Version V1.0
 */
public class SychronizedStaticTest {
    public static void main(String[] args) {

        new Thread("T1"){
            @Override
            public void run() {
                SychronizedStatic.m1();
            }
        }.start();

        new Thread("T2"){
            @Override
            public void run() {
                SychronizedStatic.m2();
            }
        }.start();

        new Thread("T3"){
            @Override
            public void run() {
                SychronizedStatic.m3();
            }
        }.start();
    }
}
