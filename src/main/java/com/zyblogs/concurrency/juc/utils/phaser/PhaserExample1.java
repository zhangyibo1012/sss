package com.zyblogs.concurrency.juc.utils.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @Title: PhaserExample1.java
 * @Package com.zyblogs.concurrency.juc.utils.phaser
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class PhaserExample1 {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {

        final Phaser phaser = new Phaser();

        IntStream.rangeClosed(1,5 ).boxed().map(i -> phaser).forEach(Task::new);

        // 注册main线程
        phaser.register();

        phaser.arriveAndAwaitAdvance();
        System.out.println("all of work finished done. ");
    }

    static class Task extends Thread{
        private final Phaser phaser;

        Task(Phaser phaser) {
            this.phaser = phaser;
            this.phaser.register();
            start();
        }

        @Override
        public void run() {
            System.out.println("The Worker [ " + getName() + "] is working ..." );
            try {
                TimeUnit.SECONDS.sleep(random .nextInt(5));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            phaser.arriveAndAwaitAdvance();
        }
    }
}
