package com.zyblogs.concurrency.thread.chapter03;

/**
 * @Title: CreateThread3.java
 * @Package com.zyblogs.concurrency.thread.chapter03
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class CreateThread3 {

    private static int counter = 0;
    private int i = 0;
    private byte[] bytes = new byte[1024];

    /**
     * JVM 创建main线程  开辟虚拟机栈stack
     * JVM will create a thread named "main"
     *
     * @param args
     */
    public static void main(String[] args) {

        //create a JVM stack

        // 局部变量表
//        int j = 0;
//
//        // 地址在局部  数据在堆
//        int[] arr = new int[1024];
        try {
            add(0);
        } catch (Error e) {
            e.printStackTrace();
            System.out.println(counter);
        }
    }

    private static void add(int i) {
        ++counter;
        add(i + 1);
    }
}
