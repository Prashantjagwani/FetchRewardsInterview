package com.fetchrewards.example.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.fetchrewards.example.model.Item
import kotlinx.coroutines.launch
import java.util.ArrayList

class MainViewModel(private val repository: MainRepository): ViewModel() {

    init {
        viewModelScope.launch { repository.getItemsFromNetwork() }
    }

    val itemsInDatabase = repository.getItemsFromDatabase()

    override fun onCleared() {
        super.onCleared()
        viewModelScope.launch {
            repository.clearDatabase()
        }
    }

    class MainViewModelFactory(private val repository: MainRepository): ViewModelProvider.Factory {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MainViewModel(repository) as T
            }
            throw IllegalArgumentException("Not able to create viewmodel")
        }
    }

    fun getByListId(id:String): LiveData<List<Item>> {
        return repository.getItemsByIdFromDatabase(id)
    }

    fun getIds(): LiveData<List<Item>> {
        return repository.getIdsFromDatabase()
    }
}