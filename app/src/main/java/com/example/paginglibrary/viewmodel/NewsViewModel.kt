package com.example.paginglibrary.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.example.paginglibrary.PagingConstants
import com.example.paginglibrary.model.NewsModel
import com.example.paginglibrary.viewmodel.repository.NewsDataSourceFactory

class NewsViewModel : ViewModel() {

    var itemPagedList: LiveData<PagedList<NewsModel>>? = null

    var liveDataSource: MutableLiveData<PageKeyedDataSource<Int, NewsModel>>? = null

    private var itemDataSourceFactory: NewsDataSourceFactory? = null

    init {
        // created data source factory
        val itemDataSourceFactory = NewsDataSourceFactory(viewModelScope)

        // get live data from factory
        liveDataSource = itemDataSourceFactory.itemLiveDataSource

        // configure paging
        val pagedListConfig = PagedList
            .Config
            .Builder()
            .setEnablePlaceholders(false)
            .setPageSize(PagingConstants.PAGE_SIZE)
            .build()

        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, pagedListConfig).build()

        this.itemDataSourceFactory = itemDataSourceFactory
    }
}