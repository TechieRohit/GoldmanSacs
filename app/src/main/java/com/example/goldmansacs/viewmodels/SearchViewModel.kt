package com.example.goldmansacs.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.goldmansacs.model.DataClass
import com.example.goldmansacs.repository.WeatherLocalRepository
import com.example.goldmansacs.repository.WeatherNetworkRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


public class SearchViewModel(application: Application) : BaseViewModel(application) {

    val weatherLocalRepository =
        WeatherLocalRepository(application)
    val weatherNetworkRepository =
        WeatherNetworkRepository()

    fun fetchData(city: String) {
        if (city.isEmpty()) {
            error.setValue("City cannot be Empty")
        } else {
            weatherNetworkRepository.getWeatherDataByCity(city)
        }
    }

    fun getData(): LiveData<DataClass.WeatherDao?>? {
        return weatherNetworkRepository.weatherData
    }

    fun getSavedData(): List<DataClass.WeatherDao> {
        return weatherLocalRepository.savedWeatherList
    }

    fun insertData(weatherData: DataClass.WeatherDao?) {
        viewModelScope.launch(Dispatchers.IO) {
            weatherLocalRepository.insertData(weatherData)
        }
    }

    fun getApiError(): LiveData<String> {
        return weatherNetworkRepository.apiError
    }
}