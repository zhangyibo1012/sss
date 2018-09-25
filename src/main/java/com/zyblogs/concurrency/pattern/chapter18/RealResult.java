package com.zyblogs.concurrency.pattern.chapter18;

import lombok.AllArgsConstructor;

/**
 * @Title: RealResult.java
 * @Package com.zyblogs.concurrency.pattern.chapter18
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
@AllArgsConstructor
public class RealResult implements Result {

    private final Object resultValue;

    @Override
    public Object getResultValue() {
        return this.resultValue;
    }
}
