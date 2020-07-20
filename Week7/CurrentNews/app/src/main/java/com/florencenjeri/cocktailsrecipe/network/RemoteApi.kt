package com.florencenjeri.cocktailsrecipe.network

import com.florencenjeri.cocktailsrecipe.model.Failure
import com.florencenjeri.cocktailsrecipe.model.News
import com.florencenjeri.cocktailsrecipe.model.Result
import com.florencenjeri.cocktailsrecipe.model.Success

class RemoteApi(val apiService: ApiService) {
    suspend fun fetchNews(): Result<List<News>> = try {
        val body = apiService.latestNews()
        Success(body.news)
    } catch (error: Throwable) {
        Failure(error)
    }
}