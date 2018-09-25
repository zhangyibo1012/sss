package com.zyblogs.concurrency.pattern.chapter01;


/**
 * @Title: SingletonObject.java
 * @Package com.zyblogs.concurrency.pattern
 * @Description: 加锁  变成串行 影响效率
 * 即能保证懒加载 又能保证线程安全 效率高 不加锁
 * @Author ZhangYB
 * @Version V1.0
 */
public class SingletonObject {

    private SingletonObject() {
    }

    private static class InstanceHolder {
        private final static SingletonObject instance = new SingletonObject();
    }

    public static SingletonObject getInstance() {
        return InstanceHolder.instance;
    }

//    public static void main(String[] args) {
//        IntStream.rangeClosed(1, 100).forEach(i -> new Thread(String.valueOf(i)) {
//            @Override
//            public void run() {
//                System.out.println(SingletonObject.getInstance());
//            }
//        }.start());
//    }


}
