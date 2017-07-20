package com.rxjava2.android.samples.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rxjava2.android.samples.MyApplication;
import com.rxjava2.android.samples.R;
import com.rxjava2.android.samples.model.Events;
import com.rxjava2.android.samples.ui.compose.ComposeOperatorExampleActivity;
import com.rxjava2.android.samples.ui.networking.NetworkingActivity;
import com.rxjava2.android.samples.ui.pagination.PaginationActivity;
import com.rxjava2.android.samples.ui.rxbus.RxBusActivity;

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        // For Multiple Rx Bus at a Time
        // Execute some code after 2 seconds have passed
/*
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                ((MyApplication) getApplication()).bus().send(new Events.TapEvent());
                ((MyApplication) getApplication()).busTwo().sendTwo(new Events.AutoEvent());
            }
        }, 3000);
*/

        //For default time delay
        ((MyApplication) getApplication()).sendAutoEvent();

    }

    public void startOperatorsActivity(View view) {
        startActivity(new Intent(SelectionActivity.this, OperatorsActivity.class));
    }

    public void startNetworkingActivity(View view) {
        startActivity(new Intent(SelectionActivity.this, NetworkingActivity.class));
    }

    public void startRxBusActivity(View view) {
        // ((MyApplication) getApplication()).sendAutoEvent();
        ((MyApplication) getApplication()).bus().send(new Events.TapEvent());
        startActivity(new Intent(SelectionActivity.this, RxBusActivity.class));
    }

    public void startPaginationActivity(View view) {
        startActivity(new Intent(SelectionActivity.this, PaginationActivity.class));
    }

    public void startComposeOperator(View view) {
        startActivity(new Intent(SelectionActivity.this, ComposeOperatorExampleActivity.class));
    }
}
