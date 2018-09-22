package com.zyblogs.concurrency.pattern.chapter04;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: Subject.java
 * @Package com.zyblogs.concurrency.pattern.chapter04
 * @Description: 主题
 * @Author ZhangYB
 * @Version V1.0
 */
public class Subject {

    private List<Observer> observers = new ArrayList<>();

    @Getter
    private int state;

    public void setState(int state) {
        if (state == this.state) {
            return;
        }
        this.state = state;
        notifyAllObserver();
    }

    /**
     * 附加
     *
     * @param observer
     */
    public void attch(Observer observer) {
        observers.add(observer);
    }

    /**
     * 通知
     */
    private void notifyAllObserver() {
        observers.stream().forEach(Observer::update);
    }
}
