package com.florencenjeri.readinglist.model.database

import androidx.lifecycle.LiveData
import com.florencenjeri.readinglist.model.Books

/**This class will manage my database queries*/
class BooksRepository(val booksDao: BooksDao) {

    fun getAllPlayers(): LiveData<List<Books>> = booksDao.getAll()

}