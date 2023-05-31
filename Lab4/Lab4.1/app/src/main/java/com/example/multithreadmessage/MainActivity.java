package com.example.multithreadmessage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

@SuppressWarnings("deprecation")
public class MainActivity extends AppCompatActivity {

    private ProgressBar pbFirst, pbSecond;
    private TextView tvMsgWorking, tvMsgReturned;
    private boolean isRunning;
    private int MAX_SEC;
    private int intTest;
    private Thread bgThread;
    private Handler handler;
    private Button btnStart;



    private void findViewByIds() {
        pbFirst = (ProgressBar) findViewById(R.id.pb_first);
        pbSecond = (ProgressBar) findViewById(R.id.pb_second);
        tvMsgWorking = (TextView) findViewById(R.id.tv_working);
        tvMsgReturned = (TextView) findViewById(R.id.tv_return);
        btnStart = (Button) findViewById(R.id.btn_start);
    }

    // init variables
    @SuppressLint("HandlerLeak")
    private void initVariables() {
        isRunning = false;
        MAX_SEC = 20;
        intTest = 1;
        pbFirst.setMax(MAX_SEC);
        pbFirst.setProgress(0);

        // Init views
        pbFirst.setVisibility(View.GONE);
        pbSecond.setVisibility(View.GONE);
        // Toast.makeText(MainActivity.this, "Handler", Toast.LENGTH_SHORT).show();

        handler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                super.handleMessage(msg);
                String returnedValue = (String) msg.obj;
                // Toast.makeText(MainActivity.this, "Handler", Toast.LENGTH_SHORT).show();

                // Do something with the value sent by background thread here ...
                tvMsgReturned.setText(getString(R.string.returned_by_bg_thread) + returnedValue);
                pbFirst.incrementProgressBy(2);

                // Testing thread's termination
                if (pbFirst.getProgress() == MAX_SEC) {
                    tvMsgReturned.setText(getString(R.string.done_background_thread_has_been_stopped));
                    tvMsgWorking.setText(getString(R.string.done));
                    pbFirst.setVisibility(View.GONE);
                    pbSecond.setVisibility(View.GONE);
                    btnStart.setVisibility(View.VISIBLE);
                    isRunning = false;
                } else {
                    tvMsgWorking.setText(getString(R.string.working) + pbFirst.getProgress());
                }
            }
        };
//        handler = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                String returnedValue = (String) msg.obj;
//
//                // Do something with the value sent by background thread here ...
//                tvMsgReturned.setText(getString(R.string.returned_by_bg_thread) + returnedValue);
//                pbFirst.incrementProgressBy(2);
//
//                // Testing thread's termination
//                if (pbFirst.getProgress() == MAX_SEC) {
//                    tvMsgReturned.setText(getString(R.string.done_background_thread_has_been_stopped));
//                    tvMsgWorking.setText(getString(R.string.done));
//                    pbFirst.setVisibility(View.GONE);
//                    pbSecond.setVisibility(View.GONE);
//                    btnStart.setVisibility(View.VISIBLE);
//                    isRunning = false;
//                } else {
//                    tvMsgWorking.setText(getString(R.string.working) + pbFirst.getProgress());
//                }
//            }
//        };
    }

    private void initBgThread() {
        bgThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int i = 0; i <MAX_SEC && isRunning; i++){
                        // Sleep one second
                        Thread.sleep(1000);

                        Random rnd = new Random();

                        // This is a locally generated value
                        String data = "Thread value: " + (int) rnd.nextInt(101);

                        // We can see change (global) class variables
                        data += getString(R.string.global_value_seen) + " " + intTest;
                        intTest ++;
                        // If thread is still alive send the message
                        if (isRunning) {
                            // Request a message token and put some data in it
                            Message msg = handler.obtainMessage(1, (String) data);
                            handler.sendMessage(msg);
                        }
                    }
                } catch (Throwable t) {

                }
            }
        });
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewByIds();
        initVariables();

        // Handle clickListenner
        btnStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Toast.makeText(MainActivity.this, "Button clicked", Toast.LENGTH_SHORT).show();
                isRunning = true;
                pbFirst.setVisibility(View.VISIBLE);
                pbSecond.setVisibility(View.VISIBLE);
                btnStart.setVisibility(View.GONE);
                bgThread.start();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        initBgThread();
    }

    @Override
    protected void onStop () {
        super.onStop();
        isRunning = false;
    }
}