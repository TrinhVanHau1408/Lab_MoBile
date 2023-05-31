package com.example.traloitinnhantrongtruonghopkhancap;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class SmsReceiver extends BroadcastReceiver {

    public static final String SMS_FORWARD_BROADCAST_RECEIVER = "sms_forward_broadcast_receiver";
    public static final String SMS_MESSAGE_ADDRESS_KEY = "sms_message_key";


    @Override
    public void onReceive(Context context, Intent intent) {
        String queryString = "Are you OK?".toLowerCase();

        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            SmsMessage[] messages = new SmsMessage[pdus.length];
            Log.d("leng", pdus.length+"");
            for (int i = 0; i < pdus.length; i++) {
                if (Build.VERSION.SDK_INT >= 23) {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                } else {
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
            }

            // Create ArrayList of OriginatingAddress of messages which contain queryString
            ArrayList<String> addresses = new ArrayList<>();

            for (SmsMessage message : messages) {
                Log.d("okk", message.getMessageBody());
                if (message!= null && message.getMessageBody().toLowerCase().contains(queryString)) {

                    addresses.add(message.getOriginatingAddress());
                }
            }

            if (addresses.size() > 0) {
                if (!MainActivity.isRunning) {

                    Intent iMain = new Intent(context,MainActivity.class);
                    iMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                            |Intent.FLAG_ACTIVITY_NEW_TASK

                    );

                    iMain.putStringArrayListExtra(SMS_MESSAGE_ADDRESS_KEY, addresses);
                    context.startActivity(iMain);
                    Log.e("check_0511_Activity", "đã khởi chạy lại main" );
                    context.sendBroadcast(iMain);
                } else {
                    // Forward these addresses to MainActivity to process
                    Log.d("app is stop", "starting1");
                    Intent iForwardBroadcastReceiver = new Intent(SMS_FORWARD_BROADCAST_RECEIVER);
                    iForwardBroadcastReceiver.putStringArrayListExtra(SMS_MESSAGE_ADDRESS_KEY, addresses);
                    context.sendBroadcast(iForwardBroadcastReceiver);
                }
            }
        }
    }
}
