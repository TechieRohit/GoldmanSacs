package com.example.goldmansacs.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.example.goldmansacs.utils.Constants
import com.example.goldmansacs.R
import com.example.goldmansacs.model.DataClass
import com.example.goldmansacs.utils.AppUtils.ConversionUtils.getHour
import com.example.goldmansacs.utils.AppUtils.ConversionUtils.toCelcius
import com.example.goldmansacs.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(),View.OnClickListener {

    private lateinit var mainViewModel: MainViewModel

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun initialize() {
        mainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val savedWeatherList = mainViewModel.getSavedData()

        if (savedWeatherList!= null && savedWeatherList.isEmpty()) {
            startActivityForResult(Intent(this,
                SearchActivity::class.java),
                Constants.REQUEST_CODE
            )
        }else {
            setDataInFields(savedWeatherList.get(0))
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.toolbar_main_searched -> {
                startActivityForResult(Intent(this,
                    SearchActivity::class.java),
                    Constants.REQUEST_CODE
                )
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val weatherDao = data?.getParcelableExtra<DataClass.WeatherDao>(Constants.WEATHER_DAO)
            setDataInFields(weatherDao)
        }
    }

    private fun setDataInFields(weatherDao: DataClass.WeatherDao?) {
        txtPlace.setText(weatherDao?.cityName)
        txtHumidity.setText("HUMIDITY  " + weatherDao?.humidity + "%")
        txtPressure.setText("Pressure: " + weatherDao?.pressure + "hPa")
        txtSunrise.setText("Sunrise: " + weatherDao?.sunrise?.getHour())
        txtSunset.setText("Sunset: " + weatherDao?.sunset?.getHour())
        txtTempmax.setText("Max Temp: " + weatherDao?.temp_max?.toCelcius())
        txtTempmin.setText("Min Temp: " + weatherDao?.temp_min?.toCelcius())
        txtTemp.setText(weatherDao?.temp?.toCelcius())
        txtFeelslike.setText("Feels like " + weatherDao?.feels_like?.toCelcius())

    }


}