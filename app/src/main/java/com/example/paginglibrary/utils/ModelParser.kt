@file:Suppress("UNREACHABLE_CODE")

package com.example.paginglibrary.utils

import com.example.paginglibrary.model.NewsModel
import com.google.gson.Gson
import com.google.gson.internal.LinkedTreeMap
import retrofit2.Response

fun Any.getNewsFromResponse(response: Response<Any>): ArrayList<NewsModel>? {
    val newsLinked: LinkedTreeMap<*, *> = response.body() as LinkedTreeMap<*, *>
    val responseData: LinkedTreeMap<*, *> = newsLinked["response"] as LinkedTreeMap<*, *>
    responseData.let { it ->
        val resultsData = it["results"]
        resultsData.let {
            val gson = Gson()
            val newsJson = gson.toJson(it)
            val type = object : com.google.gson.reflect.TypeToken<ArrayList<NewsModel>>() {}.type
            return gson.fromJson<ArrayList<NewsModel>>(newsJson, type)
        }
        return@let null
    }
    return null
}