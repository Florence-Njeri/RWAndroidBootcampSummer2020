package com.florencenjeri.currentnews.di

import com.florencenjeri.currentnews.database.NewsDatabase
import com.florencenjeri.currentnews.database.NewsRepository
import com.florencenjeri.currentnews.network.RemoteApi
import com.florencenjeri.currentnews.network.buildApiService
import com.florencenjeri.currentnews.ui.viewmodel.NewsViewModel
import org.koin.dsl.module

val newsModule = module {

    /**
     * get() invokes the constructor of the class and injects the object into it(constructor)
     */
    //News Related objects that should be created / injected into other classes
//    val apiService by lazy { buildApiService() }
//    val remoteApi by lazy { RemoteApi(apiService) }
//    val newsDao by lazy { NewsDatabase.getDatabase(App.getAppContext()).newsDao() }
//    val newsRepository by lazy { NewsRepository(newsDao) }

    single { buildApiService() }
    single { RemoteApi(get()) }
    single { NewsRepository(get()) }
    single { NewsDatabase.getDatabase(get()).newsDao() }
    single { NewsViewModel(get()) }
}