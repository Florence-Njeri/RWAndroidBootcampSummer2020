package com.florencenjeri.readinglist.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.florencenjeri.readinglist.model.Books

@Database(entities = [(Books::class)], version = 2)
abstract class BooksDatabase : RoomDatabase() {
    //Associate the dao with this database
    abstract fun booksDao(): BooksDao

}