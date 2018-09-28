package com.zyblogs.concurrency.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title: AtomicIntegerDetailsTest.java
 * @Package com.zyblogs.concurrency.atomic
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class AtomicIntegerDetailsTest {
    public static void main(String[] args) {

        /**
         * create
         */
        AtomicInteger i = new AtomicInteger();
        System.out.println(i.get());
        i = new AtomicInteger(10);
        System.out.println(i.get());

        // set
        i.set(12);
        System.out.println(i.get());

        // 在最终用的时候才会真正设值
        i.lazySet(15);
        System.out.println(i.get());

        // getAndSet
        System.out.println("===============getAndSet=================");
        AtomicInteger getAndSet = new AtomicInteger(10);
        int result = getAndSet.getAndAdd(10);
        System.out.println(result);
        System.out.println(getAndSet.get());

//        System.out.println("===============多线程getAndSet=================");
//        AtomicInteger getAndSet2 = new AtomicInteger();
//        new Thread(() -> {
//            for (int i1 = 0; i1< 10 ; i1++){
//                // getAndIncrement +1
//                int v = getAndSet2.getAndIncrement();
//                System.out.println(v);
//            }
//        }).start();
//
//        new Thread(() -> {
//            for (int k = 0; k < 10 ; k++){
//                int v = getAndSet2.getAndIncrement();
//                System.out.println( v);
//            }
//        }).start();

        System.out.println("===============getAndIncrement  =================");
        AtomicInteger atomicInteger = new AtomicInteger(1);
        // 原基础上加1
        int andIncrement = atomicInteger.getAndIncrement();
        System.out.println(andIncrement);
        System.out.println(atomicInteger.get());
        System.out.println("===============getAndIncrement=================");

        System.out.println("===============getAndDecrement====================");
        AtomicInteger atomicInteger1 = new AtomicInteger(1);
        int andDecrement = atomicInteger1.getAndDecrement();
        // 原基础上减1
        System.out.println(andDecrement);
        System.out.println(atomicInteger1.get());
        System.out.println("===============getAndDecrement====================");

        AtomicInteger atomicInteger2 = new AtomicInteger(1);
        // 返回相加后的值 i1 =2 atomicInteger2.get()=2
        int i1 = atomicInteger2.incrementAndGet();
        System.out.println(i1);
        System.out.println(atomicInteger2.get());

        System.out.println("======compareAndSet=======");
        AtomicInteger x = new AtomicInteger(10);
        // 如果compareAndSet的第一个参数等于10 ，那么更新为20 返回true
        // 反之  返回原来的值和false
        boolean flag = x.compareAndSet(10, 20);
        System.out.println(x.get());
        System.out.println(flag);

    }





}
