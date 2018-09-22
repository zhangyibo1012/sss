package com.zyblogs.concurrency.pattern.chapter05;

/**
 * @Title: Gate.java
 * @Package com.zyblogs.concurrency.pattern.chapter05
 * @Description: TODO SharedResourse共享资源
 * @Author ZhangYB
 * @Version V1.0
 */
public class Gate {

    private int counter = 0;
    private String name = "Nobody";
    private String address = "Nowhere";

    /**
     * 临界值
     * 通过
     *
     * @param name
     * @param address
     */
    public synchronized void pass(String name, String address) {
        this.counter++;
        /*race*/
        this.name = name;
        this.address = address;
        verify();
    }

    /**
     * 效验是否通过门
     */
    private void verify() {
        if (this.name.charAt(0) != this.address.charAt(0)) {
            System.out.println("*******BROKEN********" + toString());
        }
    }

    @Override
    public synchronized String toString() {
        return "No." + counter + ":" + name + "," + address;
    }
}
