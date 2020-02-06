package com.example.paginglibrary.model

data class NewsModel(
    val apiUrl: String,
    val fields: Fields? = null,
    val id: String,
    val isHosted: Boolean,
    val pillarId: String,
    val pillarName: String,
    val sectionId: String,
    val sectionName: String,
    val type: String,
    val webPublicationDate: String,
    val webTitle: String,
    val webUrl: String) {

    data class Fields(
        val thumbnail: String? = null
    )
}