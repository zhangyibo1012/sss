package com.zyblogs.concurrency.thread.chapter03;

/**
 * @Title: CreateThread.java
 * @Package com.zyblogs.concurrency.thread.chapter03
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class CreateThread {
    public static void main(String[] args) {
        Thread t1 = new Thread();
        Thread t2 = new Thread(() -> System.out.println("=============="));
        t1.start();
        t2.start();
        System.out.println(t1.getName());
        System.out.println(t2.getName());

        Thread t3 = new Thread("MyName");
        System.out.println(t3.getName());

        Thread t4 = new Thread(()-> System.err.println("Runable"));
        System.out.println(t4.getName());

        Thread t5 = new Thread(()-> System.err.println("Runable>>>" + Thread.currentThread().getName()) ,"RunnableThread");
//        System.out.println(t5.getName());
        t5.start();
    }
}
