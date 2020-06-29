package com.florencenjeri.readinglist.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

/**
 * DAO to insert and delete data from the database table (reading_list)
 */
@Dao
interface BooksDao {
    @Query("SELECT * FROM reading_list")
    fun getAll(): List<Books>

    @Insert
    fun putAll(vararg bookList: Books)

}