package com.zyblogs.concurrency.thread.chapter07;

/**
 * @Title: SynchronizedRunnable.java
 * @Package com.zyblogs.concurrency.thread.chapter07
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class SynchronizedRunnable implements Runnable{
    private int index = 1;

    private final static int MAX = 500;

    /**
     *  同步代码块
     */
    private final Object MONITOR = new Object();

    /**
     *  this
     */
    @Override
    public  void run() {
        while (true){
            if (ticket()) {
                break;
            }
        }
    }

    private synchronized boolean ticket(){

        // 1.getFiled 读操作
        if (index > MAX){
            return true;
        }

        try {
            Thread.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //index++=>index = index+1
        //1. get Field index
        //2. index = index+1
        //3. put field index
        System.out.println(Thread.currentThread() + "的号码是:" + (index++));
        return false;
    }
}
