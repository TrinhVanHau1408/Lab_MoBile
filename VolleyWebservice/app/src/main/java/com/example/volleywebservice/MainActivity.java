package com.example.volleywebservice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.VolleyError;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtRes = (TextView) findViewById(R.id.txtRes);
        // 172.17.34.20
        MyVolley.getInstance(this).makeWebServiceCall(
                "https://172.17.34.20/webservice/api.php",
                new MyVolley.VolleyCallback() {
            @Override
            public void onSuccess(String result) {
                Log.i("Connect webservice", "onSuccess: " +  result);
                txtRes.setText(result);
            }

            @Override
            public void onError(VolleyError error) {
                Log.e("Connect webservice", "onError: " +  error.getMessage());
                txtRes.setText(error.getMessage());
            }
        });
    }
}