package com.florencenjeri.currentnews.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.florencenjeri.currentnews.model.News

@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(posts: List<News>)

    //Ensure the latest news is always at the top of the RecyclerView
    @Query("SELECT * FROM news_database ORDER BY published DESC")
    fun fetchNews(): LiveData<List<News>>
}