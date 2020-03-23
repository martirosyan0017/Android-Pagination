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
import com.example.paginglibrary.PagingState.ITEM_END_LOADED
import com.example.paginglibrary.PagingState.ITEM_FRONT_LOADED
import com.example.paginglibrary.viewmodel.repository.NewsDataSourceFactory

class NewsViewModel : ViewModel() {

    var itemPagedList: LiveData<PagedList<NewsModel>>? = null
    var pagingStateLiveData = MutableLiveData<String>()

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

        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, pagedListConfig)
            .setBoundaryCallback(object: PagedList.BoundaryCallback<NewsModel>(){

                override fun onItemAtEndLoaded(itemAtEnd: NewsModel) {
                    super.onItemAtEndLoaded(itemAtEnd)
                    pagingStateLiveData.value = ITEM_END_LOADED
                }

                override fun onItemAtFrontLoaded(itemAtFront: NewsModel) {
                    super.onItemAtFrontLoaded(itemAtFront)
                    pagingStateLiveData.value = ITEM_FRONT_LOADED
                }
            })
            .build()

        this.itemDataSourceFactory = itemDataSourceFactory
    }
}