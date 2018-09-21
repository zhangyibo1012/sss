package com.zyblogs.concurrency.pattern.chapter06;

import java.util.Random;

/**
 * @Title: WriterWorker.java
 * @Package com.zyblogs.concurrency.pattern.chapter06
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class WriterWorker extends Thread{

    private static final Random random = new Random(System.currentTimeMillis());

    /**
     *  共享数据
     */
    private final SharedData data;

    private final String filler;

    private int index = 0 ;

    public WriterWorker(SharedData data , String filler){
        this.data = data;
        this.filler = filler;
    }

    @Override
    public void run() {

        try {
        while (true){
            char c = nextChar();
            data.write(c);
            Thread.sleep(random.nextInt(1_000));
            }
        }catch (InterruptedException e) {
                e.printStackTrace();
            }

    }

    private char nextChar(){
        char c = filler.charAt(index);
        index++;
        if (index >= filler.length()){
            index = 0;
        }
        return c;
    }
}
