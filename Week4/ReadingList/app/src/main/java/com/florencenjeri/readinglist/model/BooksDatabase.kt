package com.florencenjeri.readinglist.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(Books::class)], version = 1)
abstract class BooksDatabase : RoomDatabase() {
    //Associate the dao with this database
    abstract fun booksDao(): BooksDao
}