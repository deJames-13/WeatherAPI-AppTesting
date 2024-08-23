package com.example.weatherapiapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button btnCityId, btnWeatherId, btnWeatherName;
    EditText etSearch;
    ListView lvWeather;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnCityId = findViewById(R.id.btnCityId);
        btnWeatherId = findViewById(R.id.btnWeatherById);
        btnWeatherName = findViewById(R.id.btnWeatherByName);
        etSearch = findViewById(R.id.txtQueryInput);
        lvWeather = findViewById(R.id.lv_weather);

        WeatherDataService weatherDataService = new WeatherDataService(this);

        btnCityId.setOnClickListener(v -> {
            String cityName = etSearch.getText().toString();
            weatherDataService.getByCityId(cityName, new WeatherDataService.VolleyResponseListener() {
                @Override
                public void onError(String message) {
                    Log.d("error", message);
                }
                @Override
                public void onResponse(String response) {
                    Toast.makeText(MainActivity.this, "City ID is " + response, Toast.LENGTH_SHORT).show();
                }
            });


        });

        btnWeatherId.setOnClickListener(v -> {
            String id = etSearch.getText().toString();
            Toast.makeText(MainActivity.this, "Weather by ID is " + id, Toast.LENGTH_SHORT).show();
        });

        btnWeatherName.setOnClickListener(v -> {
            String name = etSearch.getText().toString();
            Toast.makeText(MainActivity.this, "Weather by Name is " + name, Toast.LENGTH_SHORT).show();
        });



    }
}