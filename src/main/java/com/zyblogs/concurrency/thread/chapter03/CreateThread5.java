package com.zyblogs.concurrency.thread.chapter03;

/**
 * @Title: CreateThread5.java
 * @Package com.zyblogs.concurrency.thread.chapter03
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class CreateThread5 {

    private static int count = 1;

    public static void main(String[] args) {

        try{
            for (int i = 0 ; i < Integer.MAX_VALUE ; i ++){
                count++;
                new Thread(() -> {
                    byte[] data = new byte[1024 * 1024 * 2 ];
                    while (true){
//                        try {
//                            Thread.sleep(1);
//                        } catch (InterruptedException e) {
//                            e.printStackTrace();
//                        }
                    }
                }).start();
            }

        }catch (Error error){

        }
        System.out.println("Total create thread nums => " + count);
    }
}
