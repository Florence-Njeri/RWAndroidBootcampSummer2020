package com.florencenjeri.readinglist.database

import androidx.lifecycle.LiveData
import com.florencenjeri.readinglist.model.Books

/**This class will manage my database queries*/
class BooksRepository(val booksDao: BooksDao) {

    suspend fun getAllBooks(): List<Books>? = booksDao.getAll()

    fun getBook(id: Long): LiveData<Books> {
        return booksDao.getBook(id)
    }

    suspend fun deleteBook(book: Books) {
        booksDao.deleteBook(book)
    }
}