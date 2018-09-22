package com.zyblogs.concurrency.pattern.chapter11;

/**
 * @Title: QueryAction.java
 * @Package com.zyblogs.concurrency.pattern.chapter11
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class QueryAction {

    public void execute() {
        try {
            Thread.sleep(1_00);
            String name = " Alex " + Thread.currentThread().getName();
            ActionContext.getActionContext().getContext().setName(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
