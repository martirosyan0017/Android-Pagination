package com.example.paginglibrary.viewmodel.repository

import com.example.paginglibrary.webservice.RetrofitClient

class NewsRepository {
    suspend fun getNews(page: Int, pageSize: Int) = RetrofitClient.instance?.api?.getNews(page, pageSize)
}