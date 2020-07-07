package com.florencenjeri.readinglist.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Author(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "nationality") val nationality: String
) : Parcelable