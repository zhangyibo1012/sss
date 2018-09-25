package com.zyblogs.concurrency.pattern.chapter16;

/**
 * @Title: AppServerClient.java
 * @Package com.zyblogs.concurrency.pattern.chapter16
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class AppServerClient {
    public static void main(String[] args) {
        AppServer server = new AppServer(13345);
        server.start();

        try {
            Thread.sleep(15_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 关闭
        server.shutdown();
    }
}
