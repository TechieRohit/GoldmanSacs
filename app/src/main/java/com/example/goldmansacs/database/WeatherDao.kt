package com.example.goldmansacs.database

import androidx.room.*
import com.example.goldmansacs.model.DataClass

@Dao
interface WeatherDao {

    @Insert
    fun insert(places: DataClass.WeatherDao)

    @Update
    fun update(places: DataClass.WeatherDao)

    @Delete
    fun delete(places: DataClass.WeatherDao)

    @Query("DELETE from weather_table")
    fun deleteAllNote()

    @Query("SELECT * FROM weather_table ORDER BY uniqueId DESC")
    fun getAllPlaces(): List<DataClass.WeatherDao>
}