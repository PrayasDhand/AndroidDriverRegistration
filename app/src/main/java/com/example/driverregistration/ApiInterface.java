package com.example.driverregistration;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("Drivers")
    Call<List<String>> getDrivers();
}
