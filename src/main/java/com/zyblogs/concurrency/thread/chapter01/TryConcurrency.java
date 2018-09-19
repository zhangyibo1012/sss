package com.zyblogs.concurrency.thread.chapter01;

/**
 * @Title: TryConcurrency.java
 * @Package com.zyblogs.concurrency.thread.chapter01
 * @Description:  尝试并发读写数据
 * @Author ZhangYB
 * @Version V1.0
 */
public class TryConcurrency {

    public static void main(String[] args) {
        Thread t=new Thread("READ-Thread"){
            @Override
            public void run() {
                println(Thread.currentThread().getName());
                readFromDataBase();
            }
        };

        t.start();

        // main thread
        t.run();

     new Thread("WRITE-Thread"){
            @Override
            public void run() {
                println(Thread.currentThread().getName());
                writeDataToFile();
            }
        }.start();
    }

    private static void readFromDataBase(){
        // read data from database and handle it.
        try {
            println("Begin read data from db.");
            Thread.sleep(1000 * 10L);
            println("Read data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("The data handle finish and successfully.");
    }

    private static void println(String message){
        System.out.println(message);
    }

    private static void writeDataToFile(){
        // write data from database and handle it.
        try {
            println("Begin write data to file.");
            Thread.sleep(2000 * 10L);
            println("Write data done and start handle it.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        println("The data handle finish and successfully.");
    }
}
