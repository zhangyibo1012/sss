package com.zyblogs.concurrency.pattern.chapter11;

/**
 * @Title: ExecutionTask.java
 * @Package com.zyblogs.concurrency.pattern.chapter11
 * @Description: TODO ExecutionTask 执行任务
 * @Author ZhangYB
 * @Version V1.0
 */
public class ExecutionTask implements Runnable{

    private QueryAction queryAction = new QueryAction();

    private QueryFromHttpAction httpAction = new QueryFromHttpAction();

    @Override
    public void run() {
        queryAction.execute();
        System.out.println("The name query successful");
        httpAction.execute();
        System.out.println("The card id query successful");

        Context context = ActionContext.getActionContext().getContext();
        System.out.println("The Name is " + context.getName() + " and CardId " + context.getCardId());
    }
}
