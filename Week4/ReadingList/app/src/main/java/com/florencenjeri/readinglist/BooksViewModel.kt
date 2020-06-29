package com.florencenjeri.readinglist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.florencenjeri.readinglist.model.Books
import com.florencenjeri.readinglist.model.database.BooksRepository


class BooksViewModel : ViewModel() {
    private var booksRepository: BooksRepository

    init {
        val booksDao = ReadingListApplication.database!!.booksDao()
        booksRepository = BooksRepository(booksDao)
    }

    fun getReadBooks(): LiveData<List<Books>> {
        return booksRepository.getAllPlayers()
    }

}