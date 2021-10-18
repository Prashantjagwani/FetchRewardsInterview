package com.fetchrewards.example.network

import com.fetchrewards.example.model.RemoteItem
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("hiring.json")
    suspend fun getItemsFromNetwork(): Response<List<RemoteItem>>
}