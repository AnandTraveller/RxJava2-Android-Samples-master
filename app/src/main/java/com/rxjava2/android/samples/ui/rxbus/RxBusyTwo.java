package com.rxjava2.android.samples.ui.rxbus;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by amitshekhar on 06/02/17.
 */

public class RxBusyTwo {

    public RxBusyTwo() {
    }

    private PublishSubject<Object> bus_two = PublishSubject.create();


    public void sendTwo(Object o) {
        bus_two.onNext(o);
    }

    public Observable<Object> toObservableTwo() {
        return bus_two;
    }


    public boolean hasObservers() {
        return bus_two.hasObservers();
    }
}
