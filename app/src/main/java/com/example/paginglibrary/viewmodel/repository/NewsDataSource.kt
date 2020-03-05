package com.example.paginglibrary.viewmodel.repository

import androidx.paging.PageKeyedDataSource
import com.example.paginglibrary.model.NewsModel
import com.example.paginglibrary.utils.PagingConstants
import com.example.paginglibrary.utils.getNewsFromResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class NewsDataSource(private val scope: CoroutineScope) : PageKeyedDataSource<Int, NewsModel>() {

    private lateinit var params: LoadParams<Int>
    private lateinit var callback: LoadCallback<Int, NewsModel>

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, NewsModel>
    ) {
        scope.launch {
            try {
                val newsInitialResponse =
                    NewsRepository().getNews(PagingConstants.PAGE, PagingConstants.PAGE_SIZE)

                newsInitialResponse?.let {
                    when {
                        it.isSuccessful -> {
                            val newsList =
                                getNewsFromResponse(
                                    it
                                )
                            PagingConstants.PAGE = PagingConstants.PAGE.plus(1)
                            callback.onResult(newsList!!, null, PagingConstants.PAGE)
                        }
                        else -> {

                        }
                    }
                }
            } catch (e: Exception) {
                e.message
            }
        }
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, NewsModel>) {
        this.params = params
        this.callback = callback

        scope.launch {
            try {
                PagingConstants.PAGE = params.key
                val newsAfterResponse =
                    NewsRepository().getNews(PagingConstants.PAGE, PagingConstants.PAGE_SIZE)

                newsAfterResponse?.let {
                    when {
                        it.isSuccessful -> {
                            val newsList =
                                getNewsFromResponse(
                                    it
                                )

                            newsList?.let { it ->
                                val key: Int? = if (it.isNotEmpty()) params.key + 1
                                else null
                                callback.onResult(it, key)
                            }

                        }
                        else -> {

                        }
                    }
                }
            } catch (e: Exception) {
                e.message
            }
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, NewsModel>) {
        scope.launch {
            try {
                PagingConstants.PAGE = params.key
                val newsBeforeResponse =
                    NewsRepository().getNews(PagingConstants.PAGE, PagingConstants.PAGE_SIZE)

                newsBeforeResponse?.let {

                    when {
                        it.isSuccessful -> {
                            val newsList =
                                getNewsFromResponse(
                                    it
                                )
                            newsList?.let {
                                val key: Int?

                                if (params.key > 1)
                                    key = params.key - 1
                                else key = null

                                callback.onResult(it, key)
                            }
                        }
                        else -> {

                        }
                    }
                }
            } catch (e: Exception) {
                e.message
            }
        }
    }
}