package com.zyblogs.concurrency.pattern.chapter18;

/**
 * @Title: Servant.java
 * @Package com.zyblogs.concurrency.pattern.chapter18
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class Servant implements ActiveObject{

    @Override
    public Result makeString(int count, char fillChar) {
        char[] buf = new char[count];
        for (int i = 0 ; i < count ; i ++){
            buf[i] = fillChar;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return new RealResult(new String(buf));
    }

    @Override
    public void displayString(String text) {
        try {
            System.out.println("Servant.displayString: " + text);
            Thread.sleep(10);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
