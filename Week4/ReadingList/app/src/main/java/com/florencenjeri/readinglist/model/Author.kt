package com.florencenjeri.readinglist.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Author(val name: String, val nationality:String):Parcelable