package com.florencenjeri.cocktailsrecipe.database

import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import com.florencenjeri.cocktailsrecipe.App
import com.florencenjeri.cocktailsrecipe.model.News
import com.florencenjeri.cocktailsrecipe.model.Success
import com.florencenjeri.cocktailsrecipe.network.NetworkStatusChecker

class NewsRepository() {
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(App.getAppContext().getSystemService(ConnectivityManager::class.java))
    }

    fun fetchNews(): LiveData<List<News>> = App.newsDao.fetchNews()

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