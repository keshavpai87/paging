package com.keshav.pagingudemy

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GitRepoServiceBuilder {
    private const val BASE_URL = "https://api.github.com/"

    //Create Logger
    private val logger = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BODY)

    //Create OkHttp client
    private val okhttp = OkHttpClient.Builder().addInterceptor(logger)

    //Create Retrofit Builder
    private val builder = Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okhttp.build())

    //Create Retrofit instance
    private val retrofit = builder.build()

    fun <T>buildService(serviceType : Class<T>) : T {
        return retrofit.create(serviceType)
    }
}