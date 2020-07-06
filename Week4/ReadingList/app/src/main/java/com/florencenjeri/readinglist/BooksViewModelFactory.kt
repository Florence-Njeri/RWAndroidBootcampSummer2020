package com.florencenjeri.readinglist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.florencenjeri.readinglist.model.database.BooksRepository

class BooksViewModelFactory(private val repository: BooksRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BooksViewModel::class.java)) {
            return BooksViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}