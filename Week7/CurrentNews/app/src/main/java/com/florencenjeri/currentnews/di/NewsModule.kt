package com.florencenjeri.currentnews.di

import com.florencenjeri.currentnews.database.NewsDatabase
import com.florencenjeri.currentnews.database.NewsRepository
import com.florencenjeri.currentnews.network.RemoteApi
import com.florencenjeri.currentnews.prefs.UserPrefs
import com.florencenjeri.currentnews.ui.viewmodel.NewsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val newsModule = module {

    /**Build all the objects that need to be injected in different classes
     * get() invokes the constructor of the class and injects the object into it(constructor)
     */
    //News Related objects that should be created / injected into other classes
    single { RemoteApi() }
    single { NewsDatabase.getDatabase(androidContext()).newsDao() }
    single { NewsRepository() }
    viewModel { NewsViewModel() }
    /**
     * I only need UserPrefs to be created if the user is logged out else, don't instantiate it as we don't need it
     */
    scope(named("UserPrefs")) {
        scoped {
            UserPrefs()
        }
    }
}