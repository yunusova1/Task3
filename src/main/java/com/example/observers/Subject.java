package com.example.observers;
public interface Subject {
    void notifyAllObservers();
    void attach(IObserver obs);
    void detach(IObserver obs);
}
