package com.example.goldmansacs.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.*

class DataClass {

    data class WeatherData(
        var base: String,
        var clouds: Clouds,
        var cod: Int,
        var coord: Coord,
        var dt: Int,
        var id: Int,
        var main: Main,
        var name: String,
        var sys: Sys,
        var timezone: Int,
        var visibility: Int,
        var weather: List<Weather>,
        var wind: Wind
    )

    @Entity(tableName = "weather_table")
    data class WeatherDao(
        @PrimaryKey(autoGenerate = true)
        var uniqueId: Int = 0,

        var cityName: String = "",
        var temp: Double = 0.0,
        var humidity: Int = 0,
        var temp_max: Double = 0.0,
        var temp_min: Double = 0.0,
        var pressure: Int = 0,
        var feels_like: Double = 0.0,
        var sunrise: Long = 0,
        var sunset: Long = 0) : Parcelable {

        constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readString().toString(),
            parcel.readDouble(),
            parcel.readInt(),
            parcel.readDouble(),
            parcel.readDouble(),
            parcel.readInt(),
            parcel.readDouble(),
            parcel.readLong(),
            parcel.readLong()
        ) {
        }

        constructor(weatherData: WeatherData) : this() {
            cityName = weatherData.name
            temp = weatherData.main.temp
             humidity = weatherData.main.humidity
             temp_max = weatherData.main.temp_max
             temp_min = weatherData.main.temp_min
             pressure = weatherData.main.pressure
             feels_like = weatherData.main.feels_like
             sunrise = weatherData?.sys?.sunrise.toLong()
             sunset = weatherData?.sys?.sunset.toLong()
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeInt(uniqueId)
            parcel.writeString(cityName)
            parcel.writeDouble(temp)
            parcel.writeInt(humidity)
            parcel.writeDouble(temp_max)
            parcel.writeDouble(temp_min)
            parcel.writeInt(pressure)
            parcel.writeDouble(feels_like)
            parcel.writeLong(sunrise)
            parcel.writeLong(sunset)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<WeatherDao> {
            override fun createFromParcel(parcel: Parcel): WeatherDao {
                return WeatherDao(parcel)
            }

            override fun newArray(size: Int): Array<WeatherDao?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class Clouds(
        var all: Int
    )

    data class Coord(
        var lat: Double,
        var lon: Double
    )

    data class Main(
        var feels_like: Double,
        var humidity: Int,
        var pressure: Int,
        var temp: Double,
        var temp_max: Double,
        var temp_min: Double
    )

    data class Sys(
        var country: String,
        var id: Int,
        var message: Double,
        var sunrise: Int,
        var sunset: Int,
        var type: Int
    )

    data class Weather(
        var description: String,
        var icon: String,
        var id: Int,
        var main: String
    )

    data class Wind(
        var deg: Int,
        var speed: Double
    )
}