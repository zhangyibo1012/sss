package com.zyblogs.concurrency.thread.chapter11;

/**
 * @Title: ThreadException.java
 * @Package com.zyblogs.concurrency.thread.chapter11
 * @Description: 线程不可以抛出异常 出现异常线程就死了
 * @Author ZhangYB
 * @Version V1.0
 */
public class ThreadException {

    private final static int A = 10;
    private final static int B = 0;

    public static void main(String[] args) {
//        Thread t = new Thread(()->{
//            try {
//                Thread.sleep(5_000);
//                int result = A / B;
//                System.out.println(result);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        });
//        t.setUncaughtExceptionHandler((thread ,e) ->{
//            System.out.println(e);
//            System.out.println(thread);
//        }) ;
//        t.start();

        new Test1().test();

    }
}
