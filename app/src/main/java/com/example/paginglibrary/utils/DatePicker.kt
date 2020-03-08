package com.example.paginglibrary.utils

import android.widget.TextView
import com.example.paginglibrary.model.NewsModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun Any.getCurrentDateString(dateFormat:TextView, model: NewsModel) {
    var df: DateFormat =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()) as DateFormat
    val d: Date? = df.parse(model.webPublicationDate)
    df = SimpleDateFormat("MMM d ,   h:mm a", Locale.getDefault())
    dateFormat.text = df.format(d!!)
}
