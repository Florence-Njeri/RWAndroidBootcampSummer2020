package com.florencenjeri.readinglist.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.florencenjeri.readinglist.model.Books

/**
 * DAO to insert and delete data from the database table (reading_list)
 */
@Dao
interface BooksDao {
    /*Live data will observe for changes made to the list of books*/
    @Query("SELECT * FROM reading_list")
    suspend fun getAll(): List<Books>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun putAll(bookList: List<Books>)

    @Query("SELECT * FROM reading_list WHERE bookId = :id")
    fun getBook(id: Long): LiveData<Books>

    @Delete
    suspend fun deleteBook(book: Books)
}