package com.zyblogs.concurrency.thread.chapter06;

/**
 * @Title: ThreadCloseGraceful.java
 * @Package com.zyblogs.concurrency.thread.chapter06
 * @Description: 线程关闭
 * @Author ZhangYB
 * @Version V1.0
 */
public class ThreadCloseGraceful {

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        worker.shutdown();
    }

    private static class Worker extends Thread {
        private volatile boolean start = true;

        @Override
        public void run() {
            while (start) {
                //
            }
        }

        public void shutdown() {
            this.start = false;
        }
    }
}
