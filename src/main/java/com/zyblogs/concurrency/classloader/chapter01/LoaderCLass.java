package com.zyblogs.concurrency.classloader.chapter01;


/**
 * @Title: LoaderCLass.java
 * @Package com.zyblogs.concurrency.classloader.chapter01
 * @Description: TODO  类加载阶段就是将class文件中的二进制数据读取到内存中,将其放在方法区,然后在堆中创建一个java.lang.Class对象,用来封装在方法区的数据结构
 * @Author ZhangYB
 * @Version V1.0
 */
public class LoaderCLass {
    public static void main(String[] args) {

        // myObject1引用在栈中本地变量表   数据在堆中
        // 创建对象过程
        // 栈本地变量表-> 堆中句柄池 堆中实例池(对象实例数据:真实数据)和方法区(数据类型:对象结构)
        //  运行类过程:方法区找到方法--堆中实例化对象--调用栈（指向堆中实例）
//        MyObject myObject1 = new MyObject();
//        MyObject myObject2 = new MyObject();
//        MyObject myObject3 = new MyObject();
//        MyObject myObject4 = new MyObject();
//
//        System.out.println(myObject1 == myObject2);
//        System.out.println(myObject1.getClass() == myObject2.getClass());

        System.out.println(Sub.x);
    }
}

/**
 *  初始化阶段是执行构造函数clinit()方法  线程安全
 *    new Thread(()->new SimpleObject()).start();它未初始化完成不可以初始化下一个对象
 *    new Thread(()->new SimpleObject()).start();
 *
 *  clinit()方法是由编译器自动收集类中的所有变量的赋值动作和静态语句块中的语句合并产生的
 *
 *  静态语句块中只能访问到定义在静态语句块之前的变量,定义在它之后的变量,只能赋值,不能访问
 *
 *  clinit()方法不需要显示的调用父类的构造函数,虚拟机会保证在子类clinit执行之前,先执行父类的clinit,因此虚拟机中首先被执行的Object的clinit方法
 *
 *  由于父类clinit方法先执行,也就意味着父类中定义的静态语句块要优于子类
 *
 *
 *
 *
 */
class MyObject{



    private static int x = 10;

    static {
        System.out.println(x);
        x = 10 + 1;
        /**
         * 静态代码块后面的你可以赋值 但是不可以访问
         *
         */
//        y = 200;
//        System.out.println(y);
    }

//    private static int y = 20;
}

class Parent{
    static {
        System.out.println("Parent.static initializer");
    }
}

class Sub extends Parent{
    public static int x = 100;
    static {
        System.out.println("Sub.static initializer");
    }
}
