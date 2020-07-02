package com.florencenjeri.readinglist.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.florencenjeri.readinglist.model.Books

/**
 * DAO to insert and delete data from the database table (reading_list)
 */
@Dao
interface BooksDao {
    /*Live data will observe for changes made to the list of books*/
    @Query("SELECT * FROM reading_list")
    fun getAll(): LiveData<List<Books>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun putAll(bookList: List<Books>)

}