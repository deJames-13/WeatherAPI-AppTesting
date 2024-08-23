package com.example.weatherapiapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.BuildConfig;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherDataService {
    private final String URL = "https://api.weatherapi.com/v1/search.json?q=";
    private final String API_KEY = "your_api_key_here";
    String woeid;
    Context context;

    public WeatherDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);
        void onResponse(String response);
    }

    public void getByCityId(String cityName, VolleyResponseListener volleyResponseListener) {

        String urlWithParams = URL + cityName + "&key=" + context.getString(R.string.WEATHER_API_KEY);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, urlWithParams, null, response -> {
            try {
                JSONObject city = response.getJSONObject(0);
                 woeid = city.getString("id");
                volleyResponseListener.onResponse(woeid);
            } catch (JSONException e) {
                volleyResponseListener.onError("City not found. Error: " + e.getMessage());
            }
        }, error -> {
            volleyResponseListener.onError("Something went wrong with the request. Error: " + error.getMessage());
        });

        WeatherSingleton.getInstance(context).getRequestQueue().add(request);

    }

    public void getByWeatherId() {}
    public void getByWeatherName() {}


}
