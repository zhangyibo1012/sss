package com.zyblogs.concurrency.thread.chapter05;

import lombok.AllArgsConstructor;

/**
 * @Title: ThreadJoin3.java
 * @Package com.zyblogs.concurrency.thread.chapter05
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ThreadJoin3 {
    public static void main(String[] args) throws InterruptedException {
        long startTimestamp = System.currentTimeMillis();
        Thread t1 = new Thread(new CaptureRunnable("M1", 10000L));
        Thread t2 = new Thread(new CaptureRunnable("M2", 30000L));
        Thread t3 = new Thread(new CaptureRunnable("M3", 15000L));

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        long endTimestamp = System.currentTimeMillis();
        System.out.printf("Save data begin timestamp is:%s, end timestamp is:%s\n", startTimestamp, endTimestamp);
    }
}

@AllArgsConstructor
class CaptureRunnable implements Runnable {

    private String machineName;

    private long spendTime;

    @Override
    public void run() {
        //do the really capture data.
        try {
            Thread.sleep(spendTime);
            System.out.printf(machineName + " completed data capture at timestamp [%s] and successfully.\n", System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getResult() {
        return machineName + " finish.";
    }
}
