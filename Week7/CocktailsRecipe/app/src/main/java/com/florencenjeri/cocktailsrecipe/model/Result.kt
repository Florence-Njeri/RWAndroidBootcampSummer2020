package com.florencenjeri.cocktailsrecipe.model

sealed class Result<out T : Any>

data class Success<out T : Any>(val data: LatestNews):Result<T>()

data class Failure(val error:Throwable):Result<Nothing>()