package com.zyblogs.concurrency.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @Title: FailedAtomicIntegerFieldUpdaterTest.java
 * @Package com.zyblogs.concurrency.atomic
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class FailedAtomicIntegerFieldUpdaterTest {

    /**
     *  不能访问private字段
     */
    @Test(expected = IllegalAccessException.class)
    public void testPrivateFiledAccessError(){
        AtomicIntegerFieldUpdater<TestMe> updater = AtomicIntegerFieldUpdater.newUpdater(TestMe.class,"i" );

        TestMe me = new TestMe();
        updater.compareAndSet(me, 0, 1);
    }
}
