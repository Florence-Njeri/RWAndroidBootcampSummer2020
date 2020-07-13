package com.florencenjeri.cocktailsrecipe.model.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.florencenjeri.cocktailsrecipe.model.New

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertNews(posts : List<New>)

    @Query("SELECT * FROM news_database")
    fun fetchNews() : LiveData<List<New>>
}