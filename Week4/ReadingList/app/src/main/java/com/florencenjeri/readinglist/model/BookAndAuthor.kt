package com.florencenjeri.readinglist.model

import androidx.room.Embedded
import androidx.room.Relation

/**
 * Since Book contains the Author object we add @Relation annotation to the book variable
 */
data class BookAndAuthor(
    @Embedded val author: Author,
    @Relation(
        parentColumn = "author",
        entityColumn = "author"
    )
    val book: Books

)