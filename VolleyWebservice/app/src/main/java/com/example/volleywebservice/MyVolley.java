package com.example.volleywebservice;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MyVolley {

    private static MyVolley instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private MyVolley(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized MyVolley getInstance(Context context) {
        if (instance == null) {
            instance = new MyVolley(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public void makeWebServiceCall(String url, final VolleyCallback callback) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callback.onSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.onError(error);
            }
        });
        addToRequestQueue(stringRequest);
    }

    public interface VolleyCallback {
        void onSuccess(String result);
        void onError(VolleyError error);
    }
}
