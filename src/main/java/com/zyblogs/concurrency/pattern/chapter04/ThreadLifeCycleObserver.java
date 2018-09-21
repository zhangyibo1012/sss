package com.zyblogs.concurrency.pattern.chapter04;

import java.util.List;

/**
 * @Title: ThreadLifeCycleObserver.java
 * @Package com.zyblogs.concurrency.pattern.chapter04
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ThreadLifeCycleObserver implements LifecycleListener {

    private  final Object LOCK = new Object();

    public void concurrentQuery(List<String> ids){
        if (ids == null || ids.isEmpty()){
            return;
        }

        ids.stream().forEach(id -> new Thread(new ObservableRunnable(this) {
            @Override
            public void run() {
                try{
                    notifyChange(new RunnableEvent(RunnableState.RUNNING , Thread.currentThread() , null));
                    System.out.println("query for id " + id);
                    Thread.sleep(1_000);

//                    int x = 1 / 0;

                    notifyChange(new RunnableEvent(RunnableState.DONE , Thread.currentThread() ,null));
                }catch (Exception e){
                    notifyChange(new RunnableEvent(RunnableState.ERROR , Thread.currentThread() ,e));
                }
            }
        },id).start());
    }

    @Override
    public void onEvent(ObservableRunnable.RunnableEvent event) {
            synchronized (LOCK){
                System.out.println("The runnable [" + event.getThread().getName()+ "] data changed and state is [" + event.getState() + "]");

                // 失败
                if (event.getCause() != null){
                    System.out.println("The runnable [ " + event.getThread().getName() + "] process failed." );
                    event.getCause().printStackTrace();
                }
            }
    }
}
