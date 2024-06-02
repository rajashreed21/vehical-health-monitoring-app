package com.example.vehicalhealthmonitoringsys

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object  RetrofitClient {
    private const val BASE_URL = "https://vehical-health-monitoring-app.onrender.com"

    private val loggingInterceptor=HttpLoggingInterceptor().apply{
        level=HttpLoggingInterceptor.Level.BODY
    }

    private val client=OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService(): ApiService =retrofit.create(ApiService::class.java)

}