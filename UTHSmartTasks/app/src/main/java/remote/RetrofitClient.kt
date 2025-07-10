package com.yourcompany.uthsmarttasks.data.remote

import com.example.uthsmarttasks.util.Constants
import com.squareup.moshi.Moshi

import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

import retrofit2.converter.moshi.MoshiConverterFactory


object RetrofitClient {

    private val loggingInterceptor = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY) // Log toàn bộ request/response body
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    val taskApiService: TaskApiService by lazy {
        retrofit.create(TaskApiService::class.java)
    }
}
