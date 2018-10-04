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
public class PhaserExample3 {

    private static final Random random = new Random(System.currentTimeMillis());

    /**
     * running
     * bicycle
     * long jump
     * 中途退赛
     *
     * @param args
     */
    public static void main(String[] args) {

        final Phaser phaser = new Phaser(5);

        for (int i = 1; i < 5; i++) {
            new Athletes(i, phaser).start();
        }

        new Athletes.InjuredAthletes(5, phaser).start();
    }


    static class Athletes extends Thread {

        private final Phaser phaser;

        private final int no;

        Athletes(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {

            try {
                sport(no, phaser, ": start running", ": end running");

                sport(no, phaser, ": start bicycle", ": end bicycle");

                sport(no, phaser, ": start long jump", ": end long jump");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        public static void sport(int no, Phaser phaser, String s, String s2) throws InterruptedException {
            System.out.println(no + s);
            TimeUnit.SECONDS.sleep(random.nextInt(5));
            System.out.println(no + s2);

            // 等待其它运动员完成任务 开始下一项
            phaser.arriveAndAwaitAdvance();
        }

        static class InjuredAthletes extends Thread {
            private final Phaser phaser;

            private final int no;

            InjuredAthletes(int no, Phaser phaser) {
                this.no = no;
                this.phaser = phaser;
            }

            @Override
            public void run() {

                try {
                    sport(no, phaser, ": start running", ": end running");

                    sport(no, phaser, ": start bicycle", ": end bicycle");

//                    System.out.println("Oh I am injured.");
//                    sport(no, phaser, ": start long jump", ": end long jump");

                    System.out.println("Oh I am injured. i will be exit");

                    // 取消注册
                    phaser.arriveAndDeregister();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}