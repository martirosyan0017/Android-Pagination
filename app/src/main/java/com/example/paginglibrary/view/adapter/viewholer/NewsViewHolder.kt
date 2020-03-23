package com.example.paginglibrary.view.adapter.viewholer

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.example.paginglibrary.listener.NewsItemClickListener
import com.example.paginglibrary.R
import com.example.paginglibrary.model.NewsModel
import com.example.paginglibrary.extentions.getCurrentDateString

class NewsViewHolder(itemView: View, private val newsItemClickListener: NewsItemClickListener) :
    RecyclerView.ViewHolder(itemView) {

    private val rootRecyclerView: LinearLayout = itemView.findViewById(R.id.third)
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
        getCurrentDateString(date,news)

        //click item
        rootRecyclerView.setOnClickListener {
            newsItemClickListener.onItemClicked(it, news)
        }
    }
}


