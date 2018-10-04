package com.zyblogs.concurrency.juc.utils.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * @Title: PhaserExample2.java
 * @Package com.zyblogs.concurrency.juc.utils.phaser
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class PhaserExample2 {

    private static final Random random = new Random(System.currentTimeMillis());

    /**
     *  running
     *  bicycle
     *  long jump
     *
     * @param args
     */
    public static void main(String[] args) {

        final Phaser phaser = new Phaser(5);

        for (int i = 1; i < 6 ; i ++){
            new Athletes(i, phaser).start();
        }
    }

    static class Athletes extends Thread{

        private final Phaser phaser;

        private final int no;

        Athletes( int no ,Phaser phaser)  {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {

            try {
                System.out.println( no + ": start running");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println( no + ": end running");

                // 等待
                phaser.arriveAndAwaitAdvance();

                System.out.println( no + ": start bicycle");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println( no + ": end bicycle");
                phaser.arriveAndAwaitAdvance();

                System.out.println( no + ": start long jump");
                TimeUnit.SECONDS.sleep(random.nextInt(5));
                System.out.println( no + ": end long jump");

                // 等待
                phaser.arriveAndAwaitAdvance();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
