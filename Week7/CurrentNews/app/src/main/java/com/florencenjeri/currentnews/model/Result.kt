package com.florencenjeri.currentnews.model

sealed class Result<out T : Any>

data class Success<out T : Any>(val data: List<News>) : Result<T>()

data class Failure(val error: Throwable) : Result<Nothing>()