package com.example.paginglibrary.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
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
    val webUrl: String
) : Parcelable {

    @Parcelize
    data class Fields(val thumbnail: String? = null) : Parcelable
}