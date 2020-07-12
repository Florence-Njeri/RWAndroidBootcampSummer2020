package com.florencenjeri.cocktailsrecipe.networking

import com.florencenjeri.cocktailsrecipe.model.*

class NewsRepository(val apiService: ApiService) {
   suspend fun fetchNews(): Result<List<New>> = try {

        val body = apiService.latestNews()

        Success(body.news)
    } catch (error: Throwable) {
        Failure(error)
    }
}