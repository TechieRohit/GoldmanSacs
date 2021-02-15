package com.example.goldmansacs.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.example.goldmansacs.R
import com.example.goldmansacs.database.WeatherDao
import com.example.goldmansacs.model.DataClass
import com.example.goldmansacs.utils.AppUtils.ConversionUtils.toCelcius

class SavedPlacesAdapter(private var weatherList: List<DataClass.WeatherDao>,private var context: Context,
                         private val sacedDataCallback : SavedData) :
    RecyclerView.Adapter<SavedPlacesAdapter.MyViewHolder>() {

    @NonNull
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.row_saved_weather, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = weatherList.get(position)
        holder.temp.setText(item?.temp?.toCelcius())
        holder.feelsLike.setText("Feels like " + item?.feels_like?.toCelcius())
        holder.city.setText(item?.cityName)
        
        holder.itemView.setOnClickListener {
            sacedDataCallback.getSavedData(item)
        }
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var temp: TextView = view.findViewById(R.id.txtTemp)
        var city: TextView = view.findViewById(R.id.txtPlace)
        var feelsLike: TextView = view.findViewById(R.id.txtFeelslike)
    }

    interface SavedData {
        fun getSavedData(weatherDao: DataClass.WeatherDao)
    }
}