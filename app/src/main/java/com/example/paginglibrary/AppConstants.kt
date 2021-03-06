package com.example.paginglibrary

object NetworkConstants {
    const val BASE_URL: String = "https://content.guardianapis.com/"
    const val API_KEY: String = "dba7115d-655d-42dc-9870-5581b72a28c1"
}

object PagingConstants {
    const val PAGE_SIZE = 5
    var PAGE = 1
}

object BundleKey {
    const val DETAIL_KEY = "model"
}

object PagingState {
    const val ITEM_END_LOADED = "ItemAtEndLoaded"
    const val ITEM_FRONT_LOADED = "ItemAtFrontLoaded"
}
