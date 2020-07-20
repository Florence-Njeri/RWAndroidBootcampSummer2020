package com.florencenjeri.cocktailsrecipe.network

import com.florencenjeri.cocktailsrecipe.model.LatestNews
import retrofit2.http.GET

interface ApiService {
    @GET("v1/latest-news")
    suspend fun latestNews(): LatestNews
}