package com.florencenjeri.currentnews.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "news_database")
data class News(
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "id") @PrimaryKey val id: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "language") val language: String,
    @ColumnInfo(name = "published") val published: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "url") val url: String
)