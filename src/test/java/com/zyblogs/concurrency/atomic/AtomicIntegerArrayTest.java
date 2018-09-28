package com.zyblogs.concurrency.atomic;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicIntegerArray;

import static org.junit.Assert.assertEquals;

/**
 * @Title: AtomicIntegerArrayTest.java
 * @Package com.zyblogs.concurrency.atomic
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class AtomicIntegerArrayTest {

    @Test
    public void testAtomicIntegerArray(){
        AtomicIntegerArray array = new AtomicIntegerArray(10);
        assertEquals(10 ,array.length());
    }

    @Test
    public void testGet(){
        AtomicIntegerArray array = new AtomicIntegerArray(10);
        assertEquals(10 ,array.length());
        // 第5个元素和0 比较 不设值默认0
        assertEquals(0 ,array.get(5));
    }

    @Test
    public void testSet(){
        AtomicIntegerArray array = new AtomicIntegerArray(10);
        // 第5个元素设置5
        array.set(5, 5);
        assertEquals(10 ,array.length());
        assertEquals(5 ,array.get(5));
    }

    @Test
    public void testGetAndSet(){
        int[] arrays = new int[10];
        arrays[5] = 5;
        AtomicIntegerArray array = new AtomicIntegerArray(arrays);

        // v = 5
        int v = array.getAndSet(5, 6);

        assertEquals(5, v);
        assertEquals(6 ,array.get(5));
    }
}
