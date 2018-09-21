package com.zyblogs.concurrency.pattern.chapter07;

/**
 * @Title: UsePersonThread.java
 * @Package com.zyblogs.concurrency.pattern.chapter07
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class UsePersonThread extends Thread{

    private Person person;

    public UsePersonThread(Person person) {
        this.person = person;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(Thread.currentThread().getName() + " print " + person);
        }
    }
}
