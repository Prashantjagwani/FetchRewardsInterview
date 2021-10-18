package com.fetchrewards.example.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {

    companion object {
        const val BASE_URL = "https://fetch-hiring.s3.amazonaws.com/"
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)
}