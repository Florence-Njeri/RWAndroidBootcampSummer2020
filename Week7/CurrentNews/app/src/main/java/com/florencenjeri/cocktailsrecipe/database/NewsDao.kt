package com.florencenjeri.cocktailsrecipe.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.florencenjeri.cocktailsrecipe.model.New

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(posts: List<New>)

    //Ensure the latest news is always at the top of the RecyclerView
    @Query("SELECT * FROM news_database ORDER BY published DESC")
    fun fetchNews(): LiveData<List<New>>
}