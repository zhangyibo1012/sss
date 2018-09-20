package com.zyblogs.concurrency.thread.chapter06;

/**
 * @Title: ThreadCloseGraceful2.java
 * @Package com.zyblogs.concurrency.thread.chapter06
 * @Description: 线程关闭
 * @Author ZhangYB
 * @Version V1.0
 */
public class ThreadCloseGraceful2 {
    private static class Worker extends Thread {

        @Override
        public void run() {
            while (true) {
                if (Thread.interrupted()){
                    break;
                }
//                try {
//                    Thread.sleep(1);
//                } catch (InterruptedException e) {
////                    e.printStackTrace();
//                    break;
//                }
            }
            //-------------
            //-------------
            //-------------
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.interrupt();
    }
}
