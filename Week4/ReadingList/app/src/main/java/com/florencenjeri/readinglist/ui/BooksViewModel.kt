package com.florencenjeri.readinglist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florencenjeri.readinglist.ReadingListApplication
import com.florencenjeri.readinglist.model.Books
import kotlinx.coroutines.launch

/**Communicates between the repository and the UI*/
class BooksViewModel : ViewModel() {

    suspend fun getReadBooks(): List<Books> {
        return ReadingListApplication.repository.getAllBooks()
    }

    fun getBook(bookId: Long): LiveData<Books> {
        return ReadingListApplication.repository.getBook(bookId)
    }

    fun deleteBook(book: Books) = viewModelScope.launch {
        ReadingListApplication.repository.deleteBook(book)
    }

    suspend fun sortData(genre: String): List<Books> {
        return getReadBooks().filter { books ->
            books.genre == genre
        }
    }
}