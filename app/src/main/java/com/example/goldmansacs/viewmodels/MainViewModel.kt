package com.example.goldmansacs.viewmodels

import android.app.Application
import com.example.goldmansacs.model.DataClass
import com.example.goldmansacs.repository.WeatherLocalRepository

public class MainViewModel(application: Application) : BaseViewModel(application) {
    
    val weatherLocalRepository = WeatherLocalRepository(application)

    fun getSavedData(): List<DataClass.WeatherDao> {
        return weatherLocalRepository.savedWeatherList
    }
}