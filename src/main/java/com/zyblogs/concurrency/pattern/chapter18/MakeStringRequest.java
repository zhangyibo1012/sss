package com.zyblogs.concurrency.pattern.chapter18;

/**
 * @Title: MakeStringRequest.java
 * @Package com.zyblogs.concurrency.pattern.chapter18
 * @Description: TODO
 *
 * {@link ActiveObject#makeString(int, char)}
 *
 * @Author ZhangYB
 * @Version V1.0
 */
public class MakeStringRequest extends MethodRequest{

   private final int count ;
   private final char fillChar;

    public MakeStringRequest(Servant servant, FutureResult futureResult ,int count ,char fillChar) {
        super(servant, futureResult);
        this.fillChar = fillChar;
        this.count =count;
    }

    @Override
    public void execute() {
        Result result = servant.makeString(count, fillChar);
        futureResult.setResult(result);
    }
}
