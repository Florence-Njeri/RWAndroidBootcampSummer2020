package com.florencenjeri.readinglist.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
/**
 * Entity to be mapped into the reading_list table
 */
@Entity(tableName = "reading_list")
data class Books(
    @PrimaryKey(autoGenerate = true)
    var bookId: Long = 0,
    @ColumnInfo(name = "image")
    val image: Int,
    @ColumnInfo(name = "title")
    val title: String,
    @Embedded
    val author: Author,
    @ColumnInfo(name = "publicationDate")
    val publicationDate: String,
    @ColumnInfo(name = "pages")
    val pages: String,
    @ColumnInfo(name = "synopsis")
    val synopsis: String,
    @ColumnInfo(name = "genre")
    val genre: String
) : Parcelable