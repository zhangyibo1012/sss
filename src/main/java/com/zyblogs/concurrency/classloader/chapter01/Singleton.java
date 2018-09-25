package com.zyblogs.concurrency.classloader.chapter01;

/**
 * @Title: Singleton.java
 * @Package com.zyblogs.concurrency.classloader.chapter01
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class Singleton {

    /**
     *  (2)
     *   x 0 y 1
     *   初始化静态变量 默认值
     *   instance = null
     *   int x 默认0
     *   int y 默认0
     *
     *   x++ 1
     *   y++ 1
     *   instance = new Singleton()
     *
     *   赋值
     *   x = 0;
     *   y = 1;
     */
//    private static Singleton instance = new Singleton();

    public static int x = 0;

    public static int y ;

    /**
     * (1)
     *  1   1
     *  int x 默认0
     *  int y 默认0
     *  instance = null
     */
     private static Singleton instance = new Singleton();

     private Singleton(){
         x++;
         y++;
     }

     public static Singleton getInstance(){
         return instance;
     }

    public static void main(String[] args) {
        Singleton singleton = getInstance();
        System.out.println(singleton.x);
        System.out.println(singleton.y);

    }
}
