package com.florencenjeri.readinglist.model.database

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.florencenjeri.readinglist.ReadingListApplication
import com.florencenjeri.readinglist.model.BooksData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class BooksDatabaseCallback(
    private val scope: CoroutineScope
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        ReadingListApplication.database.let { database ->
            scope.launch {
                val booksDao = database!!.booksDao()
                prePopulateDatabase(booksDao)
            }
        }
    }

    private fun prePopulateDatabase(booksDao: BooksDao) {

        booksDao.putAll(BooksData.booksRead.toList())
    }

}