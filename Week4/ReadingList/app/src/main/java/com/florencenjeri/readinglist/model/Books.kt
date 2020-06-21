package com.florencenjeri.readinglist.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Books(
    val image: Int,
    val title: String,
    val author: Author,
    val publicationDate: String,
    val pages: String,
    val synopsis: String,
    val genre: String
) : Parcelable