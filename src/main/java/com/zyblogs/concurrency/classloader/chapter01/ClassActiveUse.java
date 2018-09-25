package com.zyblogs.concurrency.classloader.chapter01;

import java.util.Random;

/**
 * @Title: ClassActiveUse.java
 * @Package com.zyblogs.concurrency.classloader.chapter01
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ClassActiveUse {

    static {
        System.out.println("ClassActiveUse.static initializer");
    }


    public static void main(String[] args) throws ClassNotFoundException {
//        new Obj();
//        System.out.println(I.a);
//        I.a = 100;
//        System.out.println(Obj.salary);
//        Obj.printSalary();

//        Class.forName(aa"com.zyblogs.concurrency.classloader.chapter01.Obj");

//        System.out.println(Child.age );
        // (1).子类调用父类的静态变量,只初始化父类,
//        System.out.println(Child.salary );
        // (2). 引用数组 不会初始化Obj类
        Obj[] arrays = new Obj[10];

        // (3).引用常量不会初始化 常量编译阶段就放入常量池中了

        // final修饰的常量惠子啊编译期间放到常量池,不会初始化类
//        System.out.println(Obj.salary);

        //  fianl修饰的复杂类型,在编译期间无法计算得到,会初始化类
        //  值在运行过程中才需要计算出来的 会导致类的初始化

        System.out.println(Obj.x);

    }
}

class Obj{

    public  static final  long  salary = 10_0000;

    public static final int  x  = new Random().nextInt(1_00);

    static {
        System.out.println("Obj.static initializer");
    }

    public static void printSalary(){
        System.out.println("Obj.printSalary");
    }
}

class Child extends Obj{

    public static int age = 20;

    static {
        System.out.println("Child.static initializer");
    }
}

interface I{
    int  a = 10;
}

// 访问某个类或者接口的静态变量，或者对该静态变量进行赋值操作
// 1.对某个类的静态变量进行读写   -> class
// 2.对接口中静态变量进行读取     -> interface