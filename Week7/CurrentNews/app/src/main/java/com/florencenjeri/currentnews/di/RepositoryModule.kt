package com.florencenjeri.currentnews.di

import com.florencenjeri.currentnews.database.NewsRepository
import com.florencenjeri.currentnews.network.RemoteApi
import org.koin.dsl.module

val repositoryModule = module {
    single { RemoteApi(get()) }
    single { NewsRepository(get(), get()) }
}