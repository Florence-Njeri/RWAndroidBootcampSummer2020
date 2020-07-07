package com.florencenjeri.readinglist.model.database

import androidx.lifecycle.LiveData
import com.florencenjeri.readinglist.model.Books

class BooksRepository(val booksDao: BooksDao) {

    fun getAllPlayers(): LiveData<List<Books>> {
        return booksDao.getAll()
    }
}