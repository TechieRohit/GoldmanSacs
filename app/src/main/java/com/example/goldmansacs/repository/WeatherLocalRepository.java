package com.example.goldmansacs.repository;

import android.app.Application;

import com.example.goldmansacs.model.DataClass;
import com.example.goldmansacs.database.WeatherDao;
import com.example.goldmansacs.database.WeatherDatabase;

import java.util.ArrayList;
import java.util.List;

public class WeatherLocalRepository {

    private WeatherDao weatherDao;
    private List<DataClass.WeatherDao> weatherDataList = new ArrayList<>();

    public WeatherLocalRepository(Application application) {
        weatherDao = WeatherDatabase.getInstance(application).weatherDao();
    }

    public void insertData(DataClass.WeatherDao weatherData) {
        weatherDao.insert(weatherData);
    }

    public List<DataClass.WeatherDao> getSavedWeatherList() {
        return weatherDao.getAllPlaces();
    }
}
