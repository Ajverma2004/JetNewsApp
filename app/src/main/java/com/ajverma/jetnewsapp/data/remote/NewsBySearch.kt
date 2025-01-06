package com.ajverma.jetnewsapp.data.remote

import com.ajverma.jetnewsapp.data.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsBySearch {
    @GET("v2/everything")
    suspend fun getNewsBySearch(
        @Query("q") q: String,
        @Query("sort") sort: String = "popularity",
        @Query("apikey") api: String = Constants.API_KEY
    ): News
}