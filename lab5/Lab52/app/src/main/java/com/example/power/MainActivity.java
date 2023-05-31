package com.example.power;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private PowerStateChangeReceiver powerStateChangeReceiver;

    private IntentFilter filter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       filter = new IntentFilter();
        // Đăng ký lọc sự kiện cắm sạc và rút sạc cho intentfilter
        filter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        filter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        powerStateChangeReceiver = new PowerStateChangeReceiver();
        registerReceiver(powerStateChangeReceiver, filter);


    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(powerStateChangeReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(powerStateChangeReceiver);
    }
}