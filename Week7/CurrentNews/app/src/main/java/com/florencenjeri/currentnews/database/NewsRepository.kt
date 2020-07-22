package com.florencenjeri.currentnews.database

import android.net.ConnectivityManager
import com.florencenjeri.currentnews.App
import com.florencenjeri.currentnews.model.Success
import com.florencenjeri.currentnews.network.NetworkStatusChecker

class NewsRepository() {
    companion object {
        private const val PAGE_SIZE = 30
    }

    private val networkStatusChecker by lazy {
        NetworkStatusChecker(App.getAppContext().getSystemService(ConnectivityManager::class.java))
    }

    fun fetchNews() = App.newsDao.fetchNews()

    suspend fun insertNews() {
        //Refresh the database data once a user is connected to the internet
        networkStatusChecker.performIfConnectedToInternet {
            val result = App.remoteApi.fetchNews()
            if (result is Success) {
                return App.newsDao.insertNews(result.data)
            }
        }

    }
}