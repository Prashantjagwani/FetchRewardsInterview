package com.fetchrewards.example.model

data class RemoteItem(
    val id: Int?,
    val listId: Int?,
    val name: String?
)

// Separate the domain/database/network models

fun List<RemoteItem>.asDatabaseModel(): List<LocalItem> {
    return map {
        LocalItem(
            id = it.id,
            listId = it.listId,
            name = it.name
        )
    }
}
