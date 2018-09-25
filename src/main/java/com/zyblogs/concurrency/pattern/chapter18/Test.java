package com.zyblogs.concurrency.pattern.chapter18;

/**
 * @Title: Test.java
 * @Package com.zyblogs.concurrency.pattern.chapter18
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class Test {
    /**
     *  main线程
     * @param args
     */
    public static void main(String[] args) {
   // JVM里面GC是一条优先级非常低的thread，调用时机一般是在heap堆要快满了或者当前java进程闲的蛋疼的时候，但可以调整GC的频率
        //守护线程，GC随主线程一起开始到结束  《深入java虚拟机 第二版》5.2节最后
//            System.gc();
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
        new MakerClientThread(activeObject, "Alice").start();
        new MakerClientThread(activeObject, "Bobby").start();

        new DisplayClientThread("Chris", activeObject).start();
    }
}
