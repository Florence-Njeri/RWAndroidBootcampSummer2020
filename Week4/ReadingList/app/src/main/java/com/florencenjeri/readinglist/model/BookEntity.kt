package com.florencenjeri.readinglist.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity to be mapped into the reading_list table
 */
@Entity(tableName = "reading_list")
data class BookEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    @ColumnInfo(name = "image")
    val image: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "author")
    val author: Author,
    @ColumnInfo(name = "publicationDate")
    val publicationDate: String,
    @ColumnInfo(name = "pages")
    val pages: String,
    @ColumnInfo(name = "synopsis")
    val synopsis: String,
    @ColumnInfo(name = "genre")
    val genre: String
)