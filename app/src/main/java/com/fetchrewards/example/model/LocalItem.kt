package com.fetchrewards.example.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LocalItem(
    @PrimaryKey
    val id: Int?,
    val listId: Int?,
    val name: String?
)

fun List<LocalItem>.asDomainModel(): List<Item> {
    return map {
        Item(
            id = it.id,
            listId = it.listId,
            name = it.name
        )
    }
}

fun List<String>.asDomainModel2(): List<String> {
    return map {
        it
    }
}
