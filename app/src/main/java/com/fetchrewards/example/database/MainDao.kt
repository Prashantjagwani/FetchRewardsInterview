package com.fetchrewards.example.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fetchrewards.example.model.LocalItem

@Dao
interface MainDao {

    @Query("SELECT * FROM LocalItem WHERE name <> '' ORDER BY listId asc ")
    fun getItemsFromDB(): LiveData<List<LocalItem>>

    @Query("SELECT * FROM LocalItem WHERE name <> '' AND listId == :idSelected ORDER BY listId asc ")
    fun getItemsByIdFromDB(idSelected:String): LiveData<List<LocalItem>>

    @Query("SELECT * FROM LocalItem GROUP BY listId ORDER BY listId asc ")
    fun getIdsFromDB(): LiveData<List<LocalItem>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItemsToDB(vararg shops: LocalItem)

    @Query("DELETE FROM LocalItem")
    suspend fun deleteAllItemsFromDB()
}