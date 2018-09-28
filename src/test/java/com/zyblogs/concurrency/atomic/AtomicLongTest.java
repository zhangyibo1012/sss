package com.zyblogs.concurrency.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicLong;

import static org.junit.Assert.assertEquals;

/**
 * @Title: AtomicLongTest.java
 * @Package com.zyblogs.concurrency.atomic
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class AtomicLongTest {

    @Test
    public void testCreate(){
        AtomicLong l = new AtomicLong(100);
        /**
         *  32
         *  long 64
         *  hight 32
         *  low   32
         */
        assertEquals(100 , l.get());

    }
}
