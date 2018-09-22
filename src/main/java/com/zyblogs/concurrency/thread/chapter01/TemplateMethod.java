package com.zyblogs.concurrency.thread.chapter01;

/**
 * @Title: TemplateMethod.java
 * @Package com.zyblogs.concurrency.thread.chapter01
 * @Description: 模板方法
 * @Author ZhangYB
 * @Version V1.0
 */
public class TemplateMethod {

    public static void main(String[] args) {
        TemplateMethod t1 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("*" + message + "*");
            }
        };
        t1.print("Hello Thread");

        TemplateMethod t2 = new TemplateMethod() {
            @Override
            protected void wrapPrint(String message) {
                System.out.println("+" + message + "+");
            }
        };

        t2.print("Hello Thread");

    }

    public final void print(String message) {
        System.out.println("################");
        wrapPrint(message);
        System.out.println("################");
    }

    protected void wrapPrint(String message) {
    }
}
