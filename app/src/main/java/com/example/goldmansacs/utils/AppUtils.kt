package com.example.goldmansacs.utils

import android.icu.text.DateFormat
import android.icu.text.MessageFormat.format
import java.lang.String.format
import java.math.RoundingMode
import java.sql.Date
import java.sql.Timestamp
import java.text.DecimalFormat
import java.text.MessageFormat.format
import java.text.SimpleDateFormat
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


}