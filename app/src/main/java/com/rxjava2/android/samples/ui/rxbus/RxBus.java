package com.rxjava2.android.samples.ui.rxbus;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by amitshekhar on 06/02/17.
 */

public class RxBus {

    public RxBus() {
    }

    private PublishSubject<Object> bus = PublishSubject.create();
    private PublishSubject<Object> bus_two = PublishSubject.create();

    public void send(Object o) {
        bus.onNext(o);
    }

    public void sendTwo(Object o) {
        bus_two.onNext(o);
    }

    public Observable<Object> toObservable() {
        return bus;
    }

    public Observable<Object> toObservableTwo() {
        return bus_two;
    }

    public boolean hasObservers() {
        return bus.hasObservers();
    }
}
