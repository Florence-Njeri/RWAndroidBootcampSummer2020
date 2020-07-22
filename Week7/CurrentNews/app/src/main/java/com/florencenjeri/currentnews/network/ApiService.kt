package com.florencenjeri.currentnews.network

import com.florencenjeri.currentnews.model.LatestNews
import retrofit2.http.GET

interface ApiService {
    @GET("v1/latest-news")
    suspend fun latestNews(): LatestNews
}