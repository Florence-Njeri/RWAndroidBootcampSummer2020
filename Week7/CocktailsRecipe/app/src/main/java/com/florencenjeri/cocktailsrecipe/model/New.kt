package com.florencenjeri.cocktailsrecipe.model

import kotlinx.serialization.Serializable

@Serializable
data class New(
    val author: String,
    val category: List<String>,
    val description: String,
    val id: String,
    val image: String,
    val language: String,
    val published: String,
    val title: String,
    val url: String
)