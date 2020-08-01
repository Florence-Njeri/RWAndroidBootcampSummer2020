package com.florencenjeri.currentnews.network

import com.florencenjeri.currentnews.model.Failure
import com.florencenjeri.currentnews.model.News
import com.florencenjeri.currentnews.model.Result
import com.florencenjeri.currentnews.model.Success
import org.koin.core.KoinComponent
import org.koin.core.inject

class RemoteApi : KoinComponent {
    val apiService: ApiService by inject()
    suspend fun fetchNews(): Result<List<News>> = try {
        val body = apiService.latestNews()
        Success(body.news)
    } catch (error: Throwable) {
        Failure(error)
    }
}