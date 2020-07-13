package com.florencenjeri.cocktailsrecipe.networking

import com.florencenjeri.cocktailsrecipe.model.LatestNews
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {
    @GET("v1/latest-news")
    suspend fun latestNews(): LatestNews
}