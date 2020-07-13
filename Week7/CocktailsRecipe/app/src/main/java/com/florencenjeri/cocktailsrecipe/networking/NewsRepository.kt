package com.florencenjeri.cocktailsrecipe.networking

import com.florencenjeri.cocktailsrecipe.App
import com.florencenjeri.cocktailsrecipe.model.Failure
import com.florencenjeri.cocktailsrecipe.model.New
import com.florencenjeri.cocktailsrecipe.model.Result
import com.florencenjeri.cocktailsrecipe.model.Success

class NewsRepository(val apiService: ApiService) {
    suspend fun fetchNews(): Result<List<New>> = try {

        val body = apiService.latestNews()
        App.newsDao.insertNews(body.news)
        Success(body.news)
    } catch (error: Throwable) {
        Failure(error)
    }
}