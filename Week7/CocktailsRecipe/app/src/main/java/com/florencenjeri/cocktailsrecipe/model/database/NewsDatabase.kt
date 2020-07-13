package com.florencenjeri.cocktailsrecipe.model.database

abstract class NewsDatabase {
    abstract fun newsDao(): NewsDao
}