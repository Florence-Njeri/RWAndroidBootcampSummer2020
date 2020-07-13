package com.florencenjeri.cocktailsrecipe.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "news_database")
data class LatestNews(
    @Embedded
    @ColumnInfo(name = "news")
    val news: List<New>

)