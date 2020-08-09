package com.florencenjeri.currentnews.network

import com.florencenjeri.currentnews.model.Failure
import com.florencenjeri.currentnews.model.News
import com.florencenjeri.currentnews.model.Result
import com.florencenjeri.currentnews.model.Success

class RemoteApi(val apiService: ApiService) {
    suspend fun fetchNews(): Result<List<News>> = try {
        val body = apiService.latestNews()
        Success(body.news)
    } catch (error: Throwable) {
        Failure(error)
    }
}