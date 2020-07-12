package com.florencenjeri.cocktailsrecipe.networking

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

val API_KEY = "v0NXqVADNbAQFe9EEfYZx0TZlUIay2bzPdIoXYLHIDYcQBAW"

val  BASE_URL = "https://api.currentsapi.services/v1"
fun buildClient() = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        //Log the body of the sent and received data
        level = HttpLoggingInterceptor.Level.BODY
    })
    .build()


fun buildRetrofit(): Retrofit {
//Build the content type for your Kotlin parser
    val contentType = "application/json".toMediaType()
    return Retrofit.Builder()
        .client(buildClient())
        .baseUrl(BASE_URL)
        .addConverterFactory(Json.nonstrict.asConverterFactory(contentType))
        .build()
}

fun buildApiService() =
    buildRetrofit().create(ApiService::class.java)