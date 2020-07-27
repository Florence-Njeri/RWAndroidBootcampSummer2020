package com.florencenjeri.readinglist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.florencenjeri.readinglist.database.BooksRepository
import com.florencenjeri.readinglist.model.Books
import kotlinx.coroutines.launch

/**Communicates between the repository and the UI*/
class BooksViewModel(val repository: BooksRepository) : ViewModel() {
    val _books = MutableLiveData<List<Books>>()
    val books: LiveData<List<Books>> get() = _books

    private var currentFiltering = FilterType.ALL_BOOKS

    fun getReadBooks(): LiveData<List<Books>> {
        viewModelScope.launch {
            _books.value = repository.getAllBooks()
            sortData(currentFiltering)
        }
        return books
    }

    fun getBook(bookId: Long): LiveData<Books> {
        return repository.getBook(bookId)
    }

    fun deleteBook(book: Books) = viewModelScope.launch {
        repository.deleteBook(book)
    }

    fun sortData(filteringType: FilterType): List<Books> {
        val booksToDisplay = ArrayList<Books>()
        when (filteringType) {
            FilterType.FICTION -> filterBooks(books.value, "Fiction")?.let {
                booksToDisplay.addAll(
                    it
                )
            }
            FilterType.SELF_HELP -> filterBooks(books.value, "SelfHelp")?.let {
                booksToDisplay.addAll(
                    it
                )
            }
            else -> books.value?.let { booksToDisplay.addAll(it) }
        }
        return booksToDisplay
    }

    private fun filterBooks(bookList: List<Books>?, genre: String): List<Books>? {
        if (bookList != null) {
            _books.value = bookList.filter { booksRead ->
                booksRead.genre == genre
            }
            return books.value
        }
        return emptyList()
    }
}
