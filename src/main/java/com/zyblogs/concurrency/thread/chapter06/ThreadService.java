package com.zyblogs.concurrency.thread.chapter06;

/**
 * @Title: ThreadService.java
 * @Package com.zyblogs.concurrency.thread.chapter06
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ThreadService {

    private Thread executeThread;

    private boolean finished = false;

    public void execute(Runnable task) {

        executeThread = new Thread(() -> {
            // 创建守护线程
            Thread runner = new Thread(task);
            runner.setDaemon(true);
            runner.start();
            try {
                runner.join();
                finished = true;
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        });

        executeThread.start();
    }

    public void shutdown(long mills) {
        long currentTime = System.currentTimeMillis();
        while (!finished) {
            if ((System.currentTimeMillis() - currentTime) >= mills) {
                System.out.println("任务超时，需要结束他!");
                executeThread.interrupt();
                break;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("执行线程被打断!");
                break;
            }
        }
        finished = false;
    }
}