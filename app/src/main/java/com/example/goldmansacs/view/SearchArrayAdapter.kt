package com.example.goldmansacs.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.annotation.LayoutRes
import com.example.goldmansacs.model.DataClass

class SearchArrayAdapter(context: Context, @LayoutRes private val layoutResource: Int,
                         private val weatherList: List<DataClass.WeatherDao>):
    ArrayAdapter<DataClass.WeatherDao>(context, layoutResource, weatherList),
    Filterable {

    private var weatherDataList: List<DataClass.WeatherDao> = weatherList

    override fun getCount(): Int {
        return weatherDataList.size
    }

    override fun getItem(p0: Int): DataClass.WeatherDao? {
        return weatherDataList.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        // Or just return p0
        return weatherDataList.get(p0).uniqueId.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: TextView = convertView as TextView? ?: LayoutInflater.from(context).inflate(layoutResource, parent, false) as TextView
        view.text = weatherDataList.get(position).cityName
        return view
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(charSequence: CharSequence?, filterResults: Filter.FilterResults) {
                weatherDataList = filterResults.values as List<DataClass.WeatherDao>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): Filter.FilterResults {
                val queryString = charSequence?.toString()?.toLowerCase()

                val filterResults = Filter.FilterResults()
                filterResults.values = if (queryString==null || queryString.isEmpty())
                    weatherList
                else
                    weatherList.filter {
                        it.cityName.toLowerCase().contains(queryString)
                    }
                return filterResults
            }
        }
    }
}