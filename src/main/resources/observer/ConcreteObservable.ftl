package 11package

import java.util.ArrayList;
import java.util.List;

// Concrete Observable
class ConcreteObservable implements Observable {

    private List<Observer> observers;

    private String message;

    public ConcreteObservable() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(message);
        }
    }

    public void setMessage(String message) {
        this.message = message;
        notifyObservers();
    }
}

