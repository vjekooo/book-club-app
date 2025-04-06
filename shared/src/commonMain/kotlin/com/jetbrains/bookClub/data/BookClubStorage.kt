package com.jetbrains.bookClub.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

interface BookClubStorage {
    suspend fun saveObjects(newObjects: List<BookClubObject>)

    fun getObjectById(objectId: Int): Flow<BookClubObject?>

    fun getObjects(): Flow<List<BookClubObject>>
}

class InMemoryBookClubStorage : BookClubStorage {
    private val storedObjects = MutableStateFlow(emptyList<BookClubObject>())

    override suspend fun saveObjects(newObjects: List<BookClubObject>) {
        storedObjects.value = newObjects
    }

    override fun getObjectById(objectId: Int): Flow<BookClubObject?> {
        return storedObjects.map { objects ->
            objects.find { it.objectID == objectId }
        }
    }

    override fun getObjects(): Flow<List<BookClubObject>> = storedObjects
}
