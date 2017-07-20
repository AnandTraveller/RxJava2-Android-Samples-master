package com.rxjava2.android.samples.ui.rxbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.rxjava2.android.samples.MyApplication;
import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.model.Events;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by amitshekhar on 06/02/17.
 */

public class RxBusActivity extends AppCompatActivity {

    public static final String TAG = RxBusActivity.class.getSimpleName();
    TextView textView, textView_two;
    Button button;
    private int counter = 0;
    private final CompositeDisposable disposables = new CompositeDisposable();
    //private final CompositeDisposable disposables_two = new CompositeDisposable();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposables.clear(); // do not send event after activity has been destroyed
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxbus);
        textView = (TextView) findViewById(R.id.textView);
        textView_two = (TextView) findViewById(R.id.textView_two);
        button = (Button) findViewById(R.id.button);

        disposables.add(((MyApplication) getApplication())
                .bus()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {

                        if (object instanceof Events.AutoEvent) {
                            textView.setText("Auto Event Received" + (counter++));
                        } else if (object instanceof Events.TapEvent) {
                            textView.setText("Tap Event Received");
                        }
                    }
                }));

        disposables.add(((MyApplication) getApplication())
                .busTwo()
                .toObservableTwo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object object) throws Exception {
                        if (object instanceof Events.AutoEvent) {
                            textView_two.setText("Auto Event Received" + counter++);
                        } else if (object instanceof Events.TapEvent) {
                            textView_two.setText("Tap Event Received");
                        }
                    }
                }));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MyApplication) getApplication()).bus().send(new Events.TapEvent());
            }
        });
    }

}
