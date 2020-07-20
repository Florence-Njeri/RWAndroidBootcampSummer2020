package com.florencenjeri.cocktailsrecipe.database

import android.net.ConnectivityManager
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.florencenjeri.cocktailsrecipe.App
import com.florencenjeri.cocktailsrecipe.model.Success
import com.florencenjeri.cocktailsrecipe.network.NetworkStatusChecker

class NewsRepository() {
    companion object {
        private const val PAGE_SIZE = 30
    }

    private val networkStatusChecker by lazy {
        NetworkStatusChecker(App.getAppContext().getSystemService(ConnectivityManager::class.java))
    }

    fun fetchNews() = LivePagedListBuilder(
        App.newsDao.fetchNews(), PagedList.Config.Builder()
            .setPageSize(PAGE_SIZE)
            .setEnablePlaceholders(true)
            .build()
    ).build()

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