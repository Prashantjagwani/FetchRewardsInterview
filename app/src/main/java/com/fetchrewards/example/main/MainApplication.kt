package com.fetchrewards.example.main

import android.app.Application
import android.content.Context
import com.fetchrewards.example.database.MainDatabase
import com.fetchrewards.example.network.Retrofit

class MainApplication: Application() {

    companion object {
        lateinit var appContext : Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }

    // Using "by lazy" so that the variables are only instantiated when needed.

    private val retrofit by lazy { Retrofit() }
    private val database by lazy { MainDatabase.getDatabase(this) }
    val mainRepository by lazy { MainRepository(retrofit.apiService, database.mainDao) }
}