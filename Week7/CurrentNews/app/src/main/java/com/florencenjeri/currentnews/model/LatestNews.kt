package com.florencenjeri.currentnews.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import kotlinx.serialization.Serializable

@Serializable
data class LatestNews(
    @Embedded
    @ColumnInfo(name = "news")
    val news: List<News>

)