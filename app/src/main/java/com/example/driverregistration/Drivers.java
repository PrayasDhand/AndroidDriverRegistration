package com.example.driverregistration;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Drivers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_drivers);

        TextView textView = findViewById(R.id.drivers);

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<String>> call = apiInterface.getDrivers();

        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()) {
                    List<String> drivers = response.body();
                    assert drivers != null;
                    textView.setText(drivers.toString());
                }
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                textView.setText(t.getMessage());
            }
        });
    }
}
