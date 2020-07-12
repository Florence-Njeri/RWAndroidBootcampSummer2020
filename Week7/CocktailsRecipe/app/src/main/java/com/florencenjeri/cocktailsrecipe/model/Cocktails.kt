package com.florencenjeri.cocktailsrecipe.model

import kotlinx.serialization.Serializable

@Serializable
data class Cocktails(
    val drinks: List<Drink>
)