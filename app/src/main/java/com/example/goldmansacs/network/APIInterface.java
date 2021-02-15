package com.example.goldmansacs.network;

import com.example.goldmansacs.model.DataClass;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("weather")
    Call<DataClass.WeatherData> getNewsSource(@Query("q") String city, @Query("appid") String apiKey);

}
