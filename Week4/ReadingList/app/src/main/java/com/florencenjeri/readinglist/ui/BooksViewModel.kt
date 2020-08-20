package com.florencenjeri.readinglist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florencenjeri.readinglist.database.BooksRepository
import com.florencenjeri.readinglist.model.Books
import kotlinx.coroutines.launch

/**Communicates between the repository and the UI*/
class BooksViewModel(val repository: BooksRepository) : ViewModel() {

    suspend fun getReadBooks(): List<Books> {
        return repository.getAllBooks()
    }

    fun getBook(bookId: Long): LiveData<Books> {
        return repository.getBook(bookId)
    }

    fun deleteBook(book: Books) = viewModelScope.launch {
        repository.deleteBook(book)
    }

    suspend fun sortData(genre: String): List<Books> {
        return getReadBooks().filter { books ->
            books.genre == genre
        }
    }
}