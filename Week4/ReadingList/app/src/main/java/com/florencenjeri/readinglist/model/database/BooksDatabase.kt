package com.florencenjeri.readinglist.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.florencenjeri.readinglist.model.Books
import kotlinx.coroutines.CoroutineScope

@Database(entities = [(Books::class)], version = 2)
abstract class BooksDatabase : RoomDatabase() {
    //Associate the dao with this database
    abstract fun booksDao(): BooksDao

    companion object {

        //Using a singleton pattern sing Room is resource intensive

        @Volatile
        private var INSTANCE: BooksDatabase? = null

        fun getDatabase(context: Context, coroutineScope: CoroutineScope): BooksDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BooksDatabase::class.java,
                    "reading-list-db"
                )
                    .addCallback(BooksDatabaseCallback(coroutineScope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}