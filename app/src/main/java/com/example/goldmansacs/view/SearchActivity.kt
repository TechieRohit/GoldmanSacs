package com.example.goldmansacs.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.goldmansacs.R
import com.example.goldmansacs.model.DataClass
import com.example.goldmansacs.utils.Constants
import com.example.goldmansacs.viewmodels.SearchViewModel
import kotlinx.android.synthetic.main.activity_search.*


class SearchActivity : BaseActivity(),View.OnClickListener {

    private var weatherDataList : List<DataClass.WeatherDao> = emptyList()
    private lateinit var searchViewModel : SearchViewModel
    private lateinit var autoCompleteTextView: AutoCompleteTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun layoutId(): Int {
        return R.layout.activity_search
    }

    override fun initialize() {
        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)
        autoCompleteTextView = findViewById(R.id.searchCity)
        weatherDataList = searchViewModel.getSavedData() as List<DataClass.WeatherDao>
        val adapter = SearchArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            weatherDataList
        )
        autoCompleteTextView.setAdapter(adapter)
        autoCompleteTextView.threshold = 3

        autoCompleteTextView.onItemClickListener = AdapterView.OnItemClickListener{
                parent,view,position,id->

            val selectedItem = parent.getItemAtPosition(position) as DataClass.WeatherDao
            autoCompleteTextView.setText(selectedItem.cityName)
            sendData(selectedItem)
            //Toast.makeText(applicationContext,"Selected : ${selectedItem.cityName}",Toast.LENGTH_SHORT).show()
        }

        autoCompleteTextView.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchViewModel.fetchData(searchCity.text.toString())
                true
            } else false
        })

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel::class.java)

        searchViewModel.getData()?.observe(this, Observer {
            if (it != null) {
                dismissProgressDialog()
                searchViewModel.insertData(it)
                sendData(it)
            }
        })

        searchViewModel.getApiError()?.observe(this, Observer {
            dismissProgressDialog()
            Toast.makeText(this,"" + it,Toast.LENGTH_LONG).show()
        })

        searchViewModel.error().observe(this, Observer {
            dismissProgressDialog()
            Toast.makeText(this,"" + it,Toast.LENGTH_LONG).show()
        })
    }

    fun sendData(it: DataClass.WeatherDao) {
        val intent = Intent()
        intent.putExtra(Constants.WEATHER_DAO,it)
        setResult(Activity.RESULT_OK,intent)
        finish()
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.search -> {
                showProgressDialog(getString(R.string.progress_txt))
                searchViewModel.fetchData(searchCity.text.toString())
            }
            R.id.back -> {
                finish()
            }
        }
    }


}