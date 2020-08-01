package com.florencenjeri.currentnews.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

const val API_KEY = "n5YlnB5zNgFR84AHAMJg-oKSGgGShCohW24yIkJW2cu3fOrQ"
private const val KEY_API = "apiKey"
const val BASE_URL = "https://api.currentsapi.services/"

fun buildClient() = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        //Log the body of the sent and received data
        level = HttpLoggingInterceptor.Level.BODY
    })
    .addInterceptor(buildAuthInterceptor)
    .build()


private val buildAuthInterceptor = Interceptor { chain ->
    val newUrl = chain.request().url
        .newBuilder()
        .addQueryParameter(KEY_API, API_KEY)
        .build()

    val newRequest = chain.request()
        .newBuilder()
        .url(newUrl)
        .build()

    chain.proceed(newRequest)
}

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