package com.zyblogs.concurrency.pattern.chapter12;

/**
 * @Title: BalkingClient.java
 * @Package com.zyblogs.concurrency.pattern.chapter12
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class BalkingClient {
    public static void main(String[] args) {
        BalkingData balkingData = new BalkingData("D:\\Program Files\\projects\\blog\\java-thread\\src\\main\\java\\com\\zyblogs\\concurrency\\pattern\\balking.txt","===BEGIN===");

        new CustomerThread(balkingData).start();
        new WaiterThread(balkingData).start();
    }
}
