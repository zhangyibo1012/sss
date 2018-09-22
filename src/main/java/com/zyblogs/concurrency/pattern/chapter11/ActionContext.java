package com.zyblogs.concurrency.pattern.chapter11;

/**
 * @Title: ActionContext.java
 * @Package com.zyblogs.concurrency.pattern.chapter11
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public final class ActionContext {

    private static final ThreadLocal<Context> threadLocal = new ThreadLocal<Context>() {
        @Override
        protected Context initialValue() {
            return new Context();
        }
    };

    private ActionContext() {

    }

    public static ActionContext getActionContext() {
        return ContextHolder.actionContext;
    }

    public Context getContext() {
        return threadLocal.get();
    }

    /**
     * 单例模式
     */
    private static class ContextHolder {
        private final static ActionContext actionContext = new ActionContext();
    }
}
