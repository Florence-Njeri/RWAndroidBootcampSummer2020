package com.florencenjeri.readinglist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florencenjeri.readinglist.model.Books
import com.florencenjeri.readinglist.model.database.BooksDatabase
import com.florencenjeri.readinglist.model.database.BooksRepository

/**Communicates between the repository and the UI*/
class BooksViewModel : ViewModel() {
    private val booksRepository: BooksRepository

    init {
        val booksDao =
            BooksDatabase.getDatabase(ReadingListApplication.getAppContext(), viewModelScope)
                .booksDao()
        booksRepository = BooksRepository(booksDao)
    }

    fun getReadBooks(): LiveData<List<Books>> {
        return booksRepository.getAllBooks()
    }

    fun getBook(bookId: Long): LiveData<Books> {
        return booksRepository.getBook(bookId)
    }

}