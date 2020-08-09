package com.florencenjeri.currentnews.database

import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import com.florencenjeri.currentnews.App
import com.florencenjeri.currentnews.model.News
import com.florencenjeri.currentnews.model.Success
import com.florencenjeri.currentnews.network.NetworkStatusChecker
import com.florencenjeri.currentnews.network.RemoteApi

class NewsRepository(val remoteApi: RemoteApi, val dao: NewsDao) {

    private val networkStatusChecker by lazy {
        NetworkStatusChecker(App.getAppContext().getSystemService(ConnectivityManager::class.java))
    }

    fun fetchNews(): LiveData<List<News>> = dao.fetchNews()

    suspend fun refreshNews() {
        //Refresh the database data once a user is connected to the internet
        networkStatusChecker.performIfConnectedToInternet {
            val result = remoteApi.fetchNews()
            if (result is Success) {
                return dao.insertNews(result.data)
            }
        }
    }
}