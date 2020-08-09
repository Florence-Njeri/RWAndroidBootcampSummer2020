package com.florencenjeri.currentnews.di

import com.florencenjeri.currentnews.database.NewsDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { NewsDatabase.getDatabase(get()) }

    single { get<NewsDatabase>().newsDao() }

}