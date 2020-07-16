package com.florencenjeri.readinglist.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.florencenjeri.readinglist.model.Books
import com.florencenjeri.readinglist.model.BooksData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [(Books::class)], version = 2)
abstract class BooksDatabase : RoomDatabase() {
    //Associate the dao with this database
    abstract fun booksDao(): BooksDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: BooksDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): BooksDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BooksDatabase::class.java,
                    BOOK_DB

                ).addCallback(BooksDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }

        const val BOOK_DB = "reading-list-db"
    }

    /**  insert the books data while the database is being created.*/

    class BooksDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {
        /**Method insert books to db without blocking the UI thread Dispatcher.IO since we are writing data to db*/
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE.let { database ->
                scope.launch(Dispatchers.IO) {
                    populateDatabase(database!!.booksDao())
                }
            }
        }

        private suspend fun populateDatabase(booksDao: BooksDao) {
            //If db is empty insert the list of data
            if (booksDao.getAll().value.isNullOrEmpty()) {
                booksDao.putAll(
                    BooksData.booksRead.toList()
                )
            }
        }
    }
}
