package com.example.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


fun ViewGroup.inflate(@LayoutRes id: Int): View = LayoutInflater.from(this.context).inflate(id, this, false)

fun dateTimeFormat(dateTimeMillis: Long?): String {
    val sdYear = SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.ENGLISH)
    try {
        if (dateTimeMillis != null) {
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = dateTimeMillis
            return sdYear.format(calendar.time)
        }
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return ""
}

inline fun <T> T?.ifNotNull(block: (T) -> Unit): Boolean {
    return if (this !== null) {
        block(this)
        true
    } else false
}

