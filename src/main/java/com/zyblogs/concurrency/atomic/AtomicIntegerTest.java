package com.zyblogs.concurrency.atomic;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Title: AtomicIntegerTest.java
 * @Package com.zyblogs.concurrency.atomic
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class AtomicIntegerTest {

    /**
     *  1.内存可见,保证可见性
     *  2.内存屏障 禁止指令重排序，所以volatile能在一定程度上保证有序性
     *  3.不能保证原子性 no atomic
     *   观察加入volatile关键字和没有加入volatile关键字时所生成的汇编代码发现，加入volatile关键字时，会多出一个lock前缀指令
     *  lock前缀指令实际上相当于一个内存屏障（也成内存栅栏），内存屏障会提供3个功能：
     * 　　1）它确保指令重排序时不会把其后面的指令排到内存屏障之前的位置，也不会把前面的指令排到内存屏障的后面；即在执行到内存屏障这句指令时，在它前面的操作已经全部完成；
     * 　　2）它会强制将对缓存的修改操作立即写入主存；
     * 　　3）如果是写操作，它会导致其他CPU中对应的缓存行无效
     */
    private static volatile int value = 0;

    private static Set<Integer> set = Collections.synchronizedSet(new HashSet<>());

    public static void main(String[] args) throws InterruptedException {

   /*     Thread t1 = new Thread(() -> {
            int x = 0;
            while (x < 500) {
                set.add(value);
                int tmp = value;
                System.out.println(Thread.currentThread().getName() + " : " + value);
                value += 1;
                /**
                 *  value = value + 1
                 *  (1).到主内存get
                 *  (2).执行累加1
                 *  (3).将值分配给x
                 *  (4).刷新到主内存
                 *  /

                x ++;
            }
        });

        Thread t2 = new Thread(() -> {
            int x = 0;
            while (x < 500) {
                set.add(value);
                int tmp = value;
                System.out.println(Thread.currentThread().getName() + " : " + value);
                value += 1;
                x ++;
            }
        });

        Thread t3 = new Thread(() -> {
            int x = 0;
            while (x < 500) {
                set.add(value);
                int tmp = value;
                System.out.println(Thread.currentThread().getName() + " : " + value);
                value += 1;
                x ++;
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        System.out.println( "set.size() : " + set.size());*/

        /**
         * 可见,有序,一致
         */
        // 共享变量的自增
       AtomicInteger value = new AtomicInteger();
      Thread t1 = new Thread(() -> {
          int x = 0;
          while (x < 500){
              // 先拿出值 在自增
              int v = value.getAndIncrement();
              set.add(v);
              System.out.println(Thread.currentThread().getName() + ":" + v);
              x ++;
          }
      });

        Thread t2 = new Thread(() -> {
            int x = 0;
            while (x < 500){
                // 先拿出值 在自增
                int v = value.getAndIncrement();
                set.add(v);
                System.out.println(Thread.currentThread().getName() + ":" + v);
                x ++;
            }
        });

        Thread t3 = new Thread(() -> {
            int x = 0;
            while (x < 500){
                // 先拿出值 在自增
                int v = value.getAndIncrement();
                set.add(v);
                System.out.println(Thread.currentThread().getName() + ":" + v);
                x ++;
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();

        System.out.println( "set.size() : " + set.size());
    }
}
