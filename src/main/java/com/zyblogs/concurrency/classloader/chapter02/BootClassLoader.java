package com.zyblogs.concurrency.classloader.chapter02;

/**
 * @Title: BootClassLoader.java
 * @Package com.zyblogs.concurrency.classloader.chapter02
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class BootClassLoader {

    public static void main(String[] args) throws ClassNotFoundException {

        // 根加载器
        System.out.println(System.getProperty("sun.boot.class.path"));

        System.out.println(System.getProperty("java.ext.dirs"));

        Class<?> clazz = Class.forName("com.zyblogs.concurrency.classloader.chapter02.SimpleObject");
        System.out.println(clazz.getClassLoader());

        System.out.println(clazz.getClassLoader().getParent());
        System.out.println(clazz.getClassLoader().getParent().getParent());


        // 根加载器
        Class<?> klazz = Class.forName("java.lang.String");
        System.out.println(klazz);
        System.out.println(klazz.getClassLoader());
    }
}
