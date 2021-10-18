package com.fetchrewards.example.main

import android.util.Log
import androidx.lifecycle.Transformations
import com.fetchrewards.example.database.MainDao
import com.fetchrewards.example.model.RemoteItem
import com.fetchrewards.example.model.asDatabaseModel
import com.fetchrewards.example.model.asDomainModel
import com.fetchrewards.example.network.ApiService
import retrofit2.Response

class MainRepository(private val service: ApiService, private val mainDao: MainDao) {

    fun getItemsFromDatabase() = Transformations.map(mainDao.getItemsFromDB()) {it.asDomainModel()}

    suspend fun getItemsFromNetwork() {

        try {
            val res: Response<List<RemoteItem>> = service.getItemsFromNetwork()
            val itemList = res.body()

            if (itemList != null) {

                val convertedList = itemList.asDatabaseModel().toTypedArray()
                mainDao.insertItemsToDB(*convertedList)

            } else {
                Log.d("TAG", "response error = Network list is empty")
            }
        } catch (exception: Exception) {
            Log.d("Tag", "response exception = " + exception.message.toString())
        }
    }

    suspend fun clearDatabase() = mainDao.deleteAllItemsFromDB()
}