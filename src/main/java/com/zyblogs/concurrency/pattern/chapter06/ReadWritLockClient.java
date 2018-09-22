package com.zyblogs.concurrency.pattern.chapter06;

/**
 * @Title: ReadWritLockClient.java
 * @Package com.zyblogs.concurrency.pattern.chapter06
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ReadWritLockClient {
    public static void main(String[] args) {
        final SharedData sharedData = new SharedData(10);
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new ReaderWorker(sharedData).start();
        new WriterWorker(sharedData, "qwertyuiopsdfg").start();
        new WriterWorker(sharedData, "QWERTYUIOPSDFG").start();
    }
}
