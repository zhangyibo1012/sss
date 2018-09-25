package com.zyblogs.concurrency.classloader.chapter03;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Title: MyClassLoaderTest.java
 * @Package com.zyblogs.concurrency.classloader.chapter03
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class MyClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader classLoader = new MyClassLoader("MyClassLoader");
        Class<?> aClass = classLoader.loadClass("com.zyblogs.concurrency.classloader.chapter03.MyObject");

        System.out.println(aClass);
        System.out.println(aClass.getClassLoader());
        System.out.println(classLoader.getClass().getClassLoader());

        Object obj = aClass.newInstance();
        Method method = aClass.getMethod("hello", new Class<?>[]{});
        Object result = method.invoke(obj, new Object[]{});
        System.out.println(result);
    }
}
