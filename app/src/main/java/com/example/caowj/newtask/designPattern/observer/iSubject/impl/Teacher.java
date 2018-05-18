package com.example.caowj.newtask.designPattern.observer.iSubject.impl;

import com.example.caowj.newtask.designPattern.observer.iOberver.Observer;
import com.example.caowj.newtask.designPattern.observer.iSubject.Subject;

import java.util.ArrayList;

/**
 * Created by tb on 2017/5/2.
 * 具体的主题对象(这里就是老师)
 */

public class Teacher implements Subject {
    private ArrayList<Observer> observers;

    public Teacher() {
        observers = new ArrayList<>();
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int i = observers.indexOf(observer);
        if (i >= 0) {
            observers.remove(observer);
        }
    }

    @Override
    public void notifyObservers(Object... object) {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(object);
        }
    }

    /**
     * 老师发送通知给所有家长
     *
     * @param object
     */
    public void sendMessage(Object... object) {
        notifyObservers(object);
    }
}
