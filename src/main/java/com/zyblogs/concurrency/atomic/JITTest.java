package com.zyblogs.concurrency.atomic;

/**
 * @Title: JITTest.java
 * @Package com.zyblogs.concurrency.atomic
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class JITTest {

    private volatile static boolean init = false;

    public static void main(String[] args) throws InterruptedException {

        new Thread(() -> {
            while (!init){
                // 注释这句话等价于while(true){} 空循环  不会退出线程
                // 加上这句话  会退出  建议使用volatile关键字

//                System.out.println(".");
            }
            // 非volatile修饰的boolean
            // 等价于while(true){}
        }).start();

        Thread.sleep(1_000);

        new Thread(() -> {
            init = true;
            System.out.println("set init to true.");
        }).start();
    }
}
