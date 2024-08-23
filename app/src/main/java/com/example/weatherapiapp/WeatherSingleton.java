package com.example.weatherapiapp;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class WeatherSingleton {

    private static WeatherSingleton instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    private WeatherSingleton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized WeatherSingleton getInstance(Context context) {
        if (instance == null) {
            instance = new WeatherSingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

}
