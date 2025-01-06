package com.ajverma.jetnewsapp.data.remote

import com.ajverma.jetnewsapp.data.utils.Constants
import com.ajverma.jetnewsapp.data.remote.modelForCategory.CategoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsByCategories {
    @GET("v2/top-headlines/sources")
    suspend fun getNewsByCategory(
        @Query("category") category: String,
        @Query("sort") sort: String = "popularity",
        @Query("apikey") api: String = Constants.API_KEY
    ): CategoryResponse
}