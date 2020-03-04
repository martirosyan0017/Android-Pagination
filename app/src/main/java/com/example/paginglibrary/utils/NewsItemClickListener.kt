package com.example.paginglibrary.utils

import android.view.View
import com.example.paginglibrary.model.NewsModel

interface NewsItemClickListener {
    fun onItemClicked(view: View, newsModel : NewsModel)
}