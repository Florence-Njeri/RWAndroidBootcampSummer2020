package com.florencenjeri.readinglist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florencenjeri.readinglist.database.BooksRepository
import com.florencenjeri.readinglist.model.Books
import kotlinx.coroutines.launch

/**Communicates between the repository and the UI*/
class BooksViewModel(private var booksRepository: BooksRepository) : ViewModel() {

    init {

    }

    fun getReadBooks(): LiveData<List<Books>> {
        return booksRepository.getAllBooks()
    }

    fun getBook(bookId: Long): LiveData<Books> {
        return booksRepository.getBook(bookId)
    }

    fun deleteBook(book: Books) = viewModelScope.launch {
        booksRepository.deleteBook(book)
    }

}