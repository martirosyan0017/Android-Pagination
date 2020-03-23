package com.example.paginglibrary.webservice

import com.example.paginglibrary.NetworkConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("/search")
    suspend fun getNews(
        @Query("page") page: Int,
        @Query("page-size") pageSize: Int,
        @Query("api-key") apiKey: String = NetworkConstants.API_KEY,
        @Query("show-fields") showFields: String = "thumbnail"): Response<Any>
}
