package com.zyblogs.concurrency.pattern.chapter03;

/**
 * @Title: VolatileTest.java
 * @Package com.zyblogs.concurrency.pattern.chapter03
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class VolatileTest {

    /**
     *  共享数据  volatile
     *
     *  1.保证可见性，高速缓存的一致性协议
     *  2。保证有序性，内存屏障
     *
     *   1.当cpu写入数据的时候，如果发现该变量被共享(也就是说在其它cpu中也存在该变量的副本)，会发出一个信号，通知其它cpu该变量的缓存无效
     *
     *   2.当其它cpu访问该变量的时候，重新到主内存进行获取
     *
     *   ==============================================================
     *
     *   1.原子性：
     *   对基本数据类型的变量读取和赋值是保证了原子性的，要么都成功，要么都失败，这些操作不可中断
     *   i = 10； cache 10 memory 10
     *   a=10 原子性
     *   b=a 不满足 1，读a 2.把a的值赋给b
     *   c++ 不满足 1.读c 2.加运算 3.赋值给c
     *   c=c+1 不满足 1.读c 2.加运算 3.赋值给c
     *
     *   2.可见性
     *   使用volatile保证可见性。当一个变量被volatile修饰的时候，它会保证这个变量
     *   被修改之后立即更新到主内存中，其它线程读取的时候直接到主内存拿数据，而不是从cache中拿数据。
     *
     *   3.有序性
     *    happens-before relationship
     *    1.代码执行顺序，编写在前面的发生在编写在后面的
     *    2.unlock必须发生在lock之后
     *    3.volatile修饰的变量，对一个变量的写操作先于对该变量的读操作
     *    4.传递规则：操作a先于b，b先于c，那么a肯定先于c
     *    5.线程的启动规则：start方法肯定先于线程run。直接调用run就不是一个线程了，相当于普通方法的调用
     *    6.线程中断规则：interrupt这个工作，必须发生在捕获该动作之前。
     *    7.对象销毁规则：初始化必须发生在finalize之前。
     *    8.线程终结规则:所有的操作都发生在线程死亡之前。
     *    ======================================================
     *    volatile关键字
     *      一旦一个共享变量被volatile修饰，具备两层语义：
     *      1.保证了不同线程间的可见性
     *      2.禁止对其进行重排序，也就是保证了有序性
     *      3.并未保证原子性
     *
     *   Volatile关键字
     * 1.保证重排序的是偶不会把后面的指令放到屏障的前面，也不会把前面的放到后面
     * 2.强制对缓存的修改操作立刻写入主存
     * 3.如果是写操作，他会导致其他CPU中的缓存失效
     *
     * volatile的使用场景
     * 1.状态量标记
     * volatile boolean start = true;
     * while(start){
     * 	//
     * }
     *
     * void close(){
     * 	start = false;
     * }
     *
     * 2.屏障前后的一致性
     *
     * volatile boolean init;
     *
     * ---------Thread -1------------
     * //...........
     * obj = createObj()	1;
     * init = true;		2;
     *
     * ---------Thread -2-----------
     * while(!init){
     * 	sleep();
     * }
     *
     * useTheObj(obj);
     * ----------------------------
     */
    private volatile static int INIT_VALUE = 0;

    private  final static int MAX_LIMIT = 5;

    public static void main(String[] args) {

        new Thread(() ->{
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT){
                if (localValue != INIT_VALUE){
                    System.out.println("The value updated to" + INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        },"READER").start();

        new Thread(()->{
            int localValue = INIT_VALUE;
            while (INIT_VALUE < MAX_LIMIT){
                System.out.println("Update the value to" + (++localValue));
                INIT_VALUE = localValue;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } ,"UPDATER" ).start();
    }
}
