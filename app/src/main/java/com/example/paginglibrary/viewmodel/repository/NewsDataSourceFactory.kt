package com.example.paginglibrary.viewmodel.repository

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import com.example.paginglibrary.model.NewsModel
import kotlinx.coroutines.CoroutineScope

class NewsDataSourceFactory(scope: CoroutineScope) : DataSource.Factory<Int, NewsModel>() {

    val itemLiveDataSource = MutableLiveData<PageKeyedDataSource<Int, NewsModel>>()
    var newsDataSource: NewsDataSource = NewsDataSource(scope)

    override fun create(): DataSource<Int, NewsModel> {
        itemLiveDataSource.postValue(newsDataSource)
        return newsDataSource
    }
}