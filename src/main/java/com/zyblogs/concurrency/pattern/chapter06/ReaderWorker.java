package com.zyblogs.concurrency.pattern.chapter06;

/**
 * @Title: ReaderWorker.java
 * @Package com.zyblogs.concurrency.pattern.chapter06
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ReaderWorker extends Thread {

    private final SharedData data;

    public ReaderWorker(SharedData data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
          while (true){
              char[] readBuf = data.read();
              System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(readBuf));
            }
        }catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}
