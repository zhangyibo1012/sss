package com.zyblogs.concurrency.thread.chapter10;

import java.util.Collection;

/**
 * @Title: Lock.java
 * @Package com.zyblogs.concurrency.thread.chapter10
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public interface Lock {

     class TimeOutException extends Exception{

         public TimeOutException(String message){
             super(message);
         }
    }

     void lock() throws InterruptedException;

     void lock(long mills) throws InterruptedException , TimeOutException;

     void unlock();

     Collection<Thread> getBlockedThread();

     int getBlockedSize();

}
