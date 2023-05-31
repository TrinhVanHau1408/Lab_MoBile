package com.example.connectwebservice;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WebServiceManager {
    private static final String BASE_URL = "https://172.17.34.20/webservice/api.php";

    public static void getUsers(Callback callback) {
        OkHttpClient client = new OkHttpClient.Builder().build();

        Request request = new Request.Builder()
                .url(BASE_URL)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
