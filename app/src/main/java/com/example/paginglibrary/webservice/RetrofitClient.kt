package com.example.paginglibrary.webservice

import com.example.paginglibrary.utils.NetworkConstants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    private val retrofit: Retrofit

    val api: ApiService get() = retrofit.create(ApiService::class.java)

    companion object {
        private const val BASE_URL = NetworkConstants.BASE_URL

        private var mInstance: RetrofitClient? = null

        @get:Synchronized
        val instance: RetrofitClient?
            get() {
                if (mInstance == null) {
                    mInstance = RetrofitClient()
                }
                return mInstance
            }
    }

    init {
        // configure retrofit base url, parser
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}