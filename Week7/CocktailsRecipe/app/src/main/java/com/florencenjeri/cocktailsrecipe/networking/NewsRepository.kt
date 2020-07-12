package com.florencenjeri.cocktailsrecipe.networking

import com.florencenjeri.cocktailsrecipe.model.Failure
import com.florencenjeri.cocktailsrecipe.model.LatestNews
import com.florencenjeri.cocktailsrecipe.model.Result
import com.florencenjeri.cocktailsrecipe.model.Success

class NewsRepository(val apiService: ApiService) {
   suspend fun fetchNews(): Result<LatestNews> = try {

        val body = apiService.latestNews()

        Success(body)
    } catch (error: Throwable) {
        Failure(error)
    }
}