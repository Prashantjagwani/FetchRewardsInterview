package com.fetchrewards.example.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fetchrewards.example.model.LocalItem

@Database(entities = [LocalItem::class], version = 1, exportSchema = false)

abstract class MainDatabase: RoomDatabase() {
    abstract val mainDao : MainDao

    companion object {
        @Volatile
        private var INSTANCE : MainDatabase? = null

        fun getDatabase(context: Context): MainDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MainDatabase::class.java,
                    "ItemsDatabase"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}