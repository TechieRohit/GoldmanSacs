package com.example.goldmansacs.utils

import android.content.Context
import android.net.ConnectivityManager
import java.math.RoundingMode
import java.text.DecimalFormat
import java.util.*

class AppUtils {

    object ConversionUtils {
         fun Double.toCelcius(): String{
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            return df.format((this - 32) * 5/9) + " \u2103"
        }

        fun Long.getHour(): String{
            val calendar = Calendar.getInstance(Locale.ENGLISH)
            calendar.timeInMillis = this * 1000L
            val date = android.text.format.DateFormat.format("h:mm a",calendar).toString()
            return date
        }
    }

    object NetworkUtils {
        fun isConnected(context: Context): Boolean {
            val cm =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo != null && cm.activeNetworkInfo!!
                .isConnectedOrConnecting
        }
    }


}