package com.florencenjeri.cocktailsrecipe.networking

import retrofit2.http.GET


interface ApiService {
    @GET("/latest-news")
    fun latestNews()
}