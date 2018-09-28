package com.zyblogs.concurrency.atomic;

/**
 * @Title: AtomicIntegerDetailsTest2.java
 * @Package com.zyblogs.concurrency.atomic
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class AtomicIntegerDetailsTest2 {

    private final static CompareAndSetLock lock = new CompareAndSetLock();

    public static void main(String[] args) {

        // 一个线程抢到锁 其它的全挂了 根据当前线程的名字释放锁
        for (int i = 0;i < 5; i ++){

            new Thread(() -> {
                try {
                    doSomething2();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (GetLockException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private static void doSomething() throws InterruptedException {
        synchronized (AtomicIntegerDetailsTest2.class){
            System.out.println(Thread.currentThread().getName() + " get the lock.");
            Thread.sleep(10_000);
        }
    }

    private static void doSomething2() throws InterruptedException, GetLockException {
       try {
           lock.tryLock();
           System.out.println(Thread.currentThread().getName() + " get the lock.");
           Thread.sleep(10_000);
       }finally {
           lock.unlock();
       }
    }
}
