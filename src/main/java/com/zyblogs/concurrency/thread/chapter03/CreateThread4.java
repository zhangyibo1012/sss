package com.zyblogs.concurrency.thread.chapter03;

/**
 * @Title: CreateThread4.java
 * @Package com.zyblogs.concurrency.thread.chapter03
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class CreateThread4 {

    private static int count = 1;

    public static void main(String[] args) {

        Thread t1 = new Thread(null, new Runnable() {
            @Override
            public void run() {

                try {
                    add(1);
                } catch (Error e) {
                    System.out.println(count);
                }
            }

            private void add(int i) {
                count++;
                add(i + 1);
            }
        }, "Test", 1 << 24);

        t1.start();
    }
}
