package com.florencenjeri.cocktailsrecipe.model

import kotlinx.serialization.Serializable

@Serializable
data class LatestNews(
    val news: List<New>,
    val status: String
)