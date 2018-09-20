package com.zyblogs.concurrency.thread.chapter06;

/**
 * @Title: ThreadCloseForce.java
 * @Package com.zyblogs.concurrency.thread.chapter06
 * @Description: 强制关闭
 * @Author ZhangYB
 * @Version V1.0
 */
public class ThreadCloseForce {

    public static void main(String[] args) {
        ThreadService service = new ThreadService();
        long start = System.currentTimeMillis();
        service.execute(() -> {
            //load a very heavy resource.
//            while (true) {
//            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        service.shutdown(10_000);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
