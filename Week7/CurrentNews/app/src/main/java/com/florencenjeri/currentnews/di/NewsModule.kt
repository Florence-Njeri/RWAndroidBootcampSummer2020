package com.florencenjeri.currentnews.di

import com.florencenjeri.currentnews.database.NewsDatabase
import com.florencenjeri.currentnews.database.NewsRepository
import com.florencenjeri.currentnews.network.RemoteApi
import com.florencenjeri.currentnews.ui.viewmodel.NewsViewModel
import org.koin.dsl.module

val newsModule = module {

    /**
     * get() invokes the constructor of the class and injects the object into it(constructor)
     */
    //News Related objects that should be created / injected into other classes
    single { RemoteApi() }
    single { NewsDatabase.getDatabase(get()).newsDao() }
    single { NewsRepository() }
    single { NewsViewModel() }
}