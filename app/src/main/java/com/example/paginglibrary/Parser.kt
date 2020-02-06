@file:Suppress("CAST_NEVER_SUCCEEDS", "UNREACHABLE_CODE")

package com.example.paginglibrary

import com.example.paginglibrary.model.NewsModel
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import retrofit2.Response

fun getNewsFromResponse(response: Response<Any>): ArrayList<NewsModel>? {

    val newsLinked : LinkedTreeMap<Any, Any> = response.body() as LinkedTreeMap<Any, Any>

    val responseData: LinkedTreeMap<Any, Any>? = newsLinked["response"] as LinkedTreeMap<Any, Any>

    responseData?.let { it ->

        val resultsData = it["results"]
        resultsData.let {
            val gson = Gson()
            val newsJson = gson.toJson(it)
            val type = object : com.google.gson.reflect.TypeToken<ArrayList<NewsModel>>() {}.type
            val newsList = gson.fromJson<ArrayList<NewsModel>>(newsJson, type)
            return newsList
        }

        return@let null
    }
    return null
}