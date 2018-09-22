package com.zyblogs.concurrency.pattern.chapter11;

/**
 * @Title: QueryFromHttpAction.java
 * @Package com.zyblogs.concurrency.pattern.chapter11
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class QueryFromHttpAction {

    public void execute(Context context){
        String name = context.getName();
        String cardId = getCardId(name);
        context.setCardId(cardId);
    }

    private String getCardId(String name){
        try {
            Thread.sleep(1_00);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "23648290767230 " + Thread.currentThread().getName() ;
    }
}
