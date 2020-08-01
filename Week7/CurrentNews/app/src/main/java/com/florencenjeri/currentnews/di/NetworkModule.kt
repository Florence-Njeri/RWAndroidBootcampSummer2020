package com.florencenjeri.currentnews.di

import com.florencenjeri.currentnews.network.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Build the ApiService object here so as it is inject in the RemoteApi for fetching data from the remote data source
 */
val networkModule = module {
    single(named("API_KEY")) { "n5YlnB5zNgFR84AHAMJg-oKSGgGShCohW24yIkJW2cu3fOrQ" }
    single(named("KEY_API")) { "apiKey" }
    single(named("BASE_URL")) { "https://api.currentsapi.services/" }
    single {
        Interceptor { chain ->
            val newUrl = chain.request().url
                .newBuilder()
                .addQueryParameter(get<String>(named("KEY_API")), get<String>(named("API_KEY")))
                .build()

            val newRequest = chain.request()
                .newBuilder()
                .url(newUrl)
                .build()

            chain.proceed(newRequest)
        }
    }
    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                //Log the body of the sent and received data
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(get<Interceptor>())
            .build()
    }
    single<Retrofit> {
        //Build the content type for your Kotlin parser
        val contentType = "application/json".toMediaType()
        Retrofit.Builder()
            .client(get())
            .baseUrl(get<String>(named("BASE_URL")))
            .addConverterFactory(Json.nonstrict.asConverterFactory(contentType))
            .build()
    }
    single<ApiService> {
        get<Retrofit>().create(ApiService::class.java)
    }
}