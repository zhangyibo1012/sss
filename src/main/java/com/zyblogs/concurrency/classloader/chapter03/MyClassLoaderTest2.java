package com.zyblogs.concurrency.classloader.chapter03;

/**
 * @Title: MyClassLoaderTest2.java
 * @Package com.zyblogs.concurrency.classloader.chapter03
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
/**
 * 1.类加载器的委托是优先交给父亲加载器先去尝试加载
 * 2.父加载器和子加载器其实是一种包装关系，或者包含关系
 *  java.lang.String 可以打破双亲委托机制,但是java做了安全限制
 *
 *  Class卸载:
 *  1.该类所有实例都已经被gc
 *  2.加载该类的classLoader实例已经被gc
 *  3.该类的class对象没有任何地方被引用
 *
 *  GC时机不可控  那么对于class卸载也是不可控的
 */
public class MyClassLoaderTest2 {
    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader classLoader1 = new MyClassLoader("MyClassLoader-1");
        MyClassLoader classLoader2 = new MyClassLoader("MyClassLoader-2");
        classLoader2.setDir("F:\\大数据\\classloader2");

        Class<?> aClass = classLoader1.loadClass("com.zyblogs.concurrency.classloader.chapter03.MyObject");
        System.out.println(aClass.hashCode());
        Class<?> aClass2 = classLoader2.loadClass("com.zyblogs.concurrency.classloader.chapter03.MyObject");
        System.out.println(aClass2.hashCode());
        //System.out.println(((MyClassLoader) aClass.getClassLoader()).getClassLoaderName());
    }
}