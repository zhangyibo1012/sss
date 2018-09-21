package com.zyblogs.concurrency.pattern.chapter08;

/**
 * @Title: SyncInvoker.java
 * @Package com.zyblogs.concurrency.pattern.chapter08
 * @Description: 异步调用
 * @Author ZhangYB
 * @Version V1.0
 */
public class SyncInvoker {
    public static void main(String[] args) throws InterruptedException {
//        String result = get();
//        System.out.println(result);

        FutureService futureService = new FutureService();
        Future<String> future = futureService.submit(() -> {
            try {
                Thread.sleep(1_0000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        });

        System.out.println("==================");
        System.out.println("do other thing.");
        Thread.sleep(1_000);
        System.out.println("==================");

        // 获取执行结果
        System.out.println(future.get());
    }

    private static String get(){
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "FINISH";
    }

}
