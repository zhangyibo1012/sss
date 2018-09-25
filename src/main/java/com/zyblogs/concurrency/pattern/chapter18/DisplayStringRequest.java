package com.zyblogs.concurrency.pattern.chapter18;

/**
 * @Title: DisplayStringRequest.java
 * @Package com.zyblogs.concurrency.pattern.chapter18
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class DisplayStringRequest extends MethodRequest{

    private final String text;

    public DisplayStringRequest(Servant servant, final String text) {
        super(servant, null);
        this.text = text;
    }

    @Override
    public void execute() {
        this.servant.displayString(text);
    }
}
