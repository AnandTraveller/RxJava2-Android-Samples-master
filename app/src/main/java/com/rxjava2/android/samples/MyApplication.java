package com.rxjava2.android.samples;

import android.app.Application;

import com.rxjava2.android.samples.model.Events;
import com.rxjava2.android.samples.ui.rxbus.RxBus;
import com.rxjava2.android.samples.ui.rxbus.RxBusyTwo;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Created by threshold on 2017/1/12.
 */

public class MyApplication extends Application {

    public static final String TAG = "MyApplication";
    private RxBus bus;
    private RxBusyTwo busTwo;
    private int counter;

    @Override
    public void onCreate() {
        super.onCreate();
        bus = new RxBus();
        busTwo = new RxBusyTwo();
    }

    public RxBus bus() {
        return bus;
    }

    public RxBusyTwo busTwo() {
        return busTwo;
    }

    public void sendAutoEvent() {
        Observable.interval(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        bus.send(new Events.AutoEvent());
                    }
                });
    }

}
