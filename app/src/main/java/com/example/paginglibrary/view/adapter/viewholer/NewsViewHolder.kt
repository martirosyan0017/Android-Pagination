package com.example.paginglibrary.view.adapter.viewholer

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.paginglibrary.R
import com.example.paginglibrary.model.NewsModel
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val sectionName: TextView = itemView.findViewById(R.id.section_name)
    private val type: TextView = itemView.findViewById(R.id.type)
    private val title: TextView = itemView.findViewById(R.id.title1)
    private val date: TextView = itemView.findViewById(R.id.date)
    private val imageView: ImageView = itemView.findViewById(R.id.image_news)

    fun bind(news: NewsModel) {
        //Coil
        imageView.load(news.fields?.thumbnail)

        sectionName.text = news.sectionName
        type.text = news.type
        title.text = news.webTitle
        getCurrentDateString(news)
    }

    private fun getCurrentDateString(model: NewsModel) {
        var df: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val d: Date? = df.parse(model.webPublicationDate)
        df = SimpleDateFormat("MMM d ,   h:mm a", Locale.getDefault())
        date.text = df.format(d!!)
    }
}


