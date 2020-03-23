package com.example.paginglibrary.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.paginglibrary.listener.NewsItemClickListener

import com.example.paginglibrary.R
import com.example.paginglibrary.model.NewsModel
import com.example.paginglibrary.view.adapter.viewholer.NewsViewHolder

class NewsAdapter(private var clickItem: NewsItemClickListener) :
    PagedListAdapter<NewsModel, NewsViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view, parent, false)
        return NewsViewHolder(view, clickItem)
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val newsModel = getItem(position)
        newsModel?.let {
            holder.bind(it)
        }
    }


    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<NewsModel> =
            object : DiffUtil.ItemCallback<NewsModel>() {
                override fun areItemsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
                    return oldItem === newItem
                }

                override fun areContentsTheSame(oldItem: NewsModel, newItem: NewsModel): Boolean {
                    return oldItem == newItem
                }
            }
    }
}