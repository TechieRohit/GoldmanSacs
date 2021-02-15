package com.example.goldmansacs.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.goldmansacs.model.DataClass;
import com.example.goldmansacs.network.APIClient;
import com.example.goldmansacs.network.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherNetworkNetworkRepository extends BaseNetworkRepository {

    private MutableLiveData<DataClass.WeatherDao> weatherDaoMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<String> apiError = new MutableLiveData<>();
    private APIInterface apiInterface;

    public WeatherNetworkNetworkRepository() {
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    public void getWeatherDataByCity(String city) {
        apiInterface.getNewsSource(city,key()).enqueue(new Callback<DataClass.WeatherData>() {
            @Override
            public void onResponse(Call<DataClass.WeatherData> call, Response<DataClass.WeatherData> response) {
                if (response != null && response.isSuccessful()) {
                    weatherDaoMutableLiveData.setValue(new DataClass.WeatherDao(response.body()));
                }else {
                    apiError.setValue("No data found for the entered City");
                }
            }

            @Override
            public void onFailure(Call<DataClass.WeatherData> call, Throwable t) {
                apiError.setValue(t.getMessage());
            }
        });
    }

    public LiveData<DataClass.WeatherDao> getWeatherData() {
        return weatherDaoMutableLiveData;
    }

    public LiveData<String> getApiError() {
        return apiError;
    }
}
