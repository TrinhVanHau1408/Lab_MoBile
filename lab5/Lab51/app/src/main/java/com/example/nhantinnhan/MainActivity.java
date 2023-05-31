package com.example.nhantinnhan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private BroadcastReceiver broadcastReceiver;
    private IntentFilter filter;

    public void processReceive(Context context, Intent intent) {
        Toast.makeText(context, getString(R.string.you_have_a_new_message), Toast.LENGTH_SHORT).show();

        TextView tvContent = (TextView) findViewById(R.id.tv_content);

        // Use 'pdus' as key to get message
        final String SMS_EXTRA = "pdus";
        Bundle bundle = intent.getExtras();

        Object[] messages = (Object[]) bundle.get(SMS_EXTRA);
        String sms = "";

        SmsMessage smsMsg;

        for (int i = 0; i < messages.length; i++) {
            if (Build.VERSION.SDK_INT >=23) {
                smsMsg = SmsMessage.createFromPdu( (byte[]) messages[i]);
            } else {
                smsMsg = SmsMessage.createFromPdu((byte[]) messages[i]);
            }

            // Get message body
            String msgBody = smsMsg.getMessageBody();

            // Get source address of message
            String address = smsMsg.getDisplayOriginatingAddress();
            sms += address + ":\n" + msgBody + '\n';
        }

        // Show messages in textview
        tvContent.setText(sms);
    }

    private void initBroadcastReceiver() {
        // Create fitter to listen to incoming messages

        filter = new IntentFilter("android.provider.Telephony.SMS_RECEIVED");

        // Create broadcastRecevier
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // Process when a message comes
                processReceive(context, intent);

            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Make sure broadcastReceiver vas creted
        if (broadcastReceiver == null) initBroadcastReceiver();

        // Register Reciver
        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        // UnregisterReceiver when app is destroyed
        unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initBroadcastReceiver();
    }
}