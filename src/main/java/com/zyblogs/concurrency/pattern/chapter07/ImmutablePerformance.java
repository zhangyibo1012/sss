package com.zyblogs.concurrency.pattern.chapter07;

/**
 * @Title: ImmutablePerformance.java
 * @Package com.zyblogs.concurrency.pattern.chapter07
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ImmutablePerformance {
    public static void main(String[] args) throws InterruptedException {

        //16224 可变
        //16431 immutable不可变
        long startTimestamp = System.currentTimeMillis();
//        SyncObj synObj = new SyncObj();
//        synObj.setName("Alex");

        ImmutableObj immutableObj = new ImmutableObj("Alex");

        Thread t1 = new Thread(() -> {
            for (long l = 0L; l < 1000000; l++) {
                System.out.println(Thread.currentThread().getName() + "=" + immutableObj.toString());
            }
        });
        t1.start();

        Thread t2 = new Thread(() -> {
            for (long l = 0L; l < 1000000; l++) {
                System.out.println(Thread.currentThread().getName() + "=" + immutableObj.toString());
            }
        });
        t2.start();
        t1.join();
        t2.join();


        long endTimestamp = System.currentTimeMillis();
        System.out.println("Elapsed time " + (endTimestamp - startTimestamp));
    }

}

/**
 *  不可变
 */
final class ImmutableObj {
    private final String name;

    ImmutableObj(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "[" + name + "]";
    }
}

/**
 *  可变
 */
class SyncObj {

    private String name;

    public synchronized void setName(String name) {
        this.name = name;
    }

    @Override
    public synchronized String toString() {
        return "[" + name + "]";
    }
}
