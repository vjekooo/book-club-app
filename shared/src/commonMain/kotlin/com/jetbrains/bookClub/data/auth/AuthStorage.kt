package com.jetbrains.bookClub.data.bookClub.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

interface AuthStorage {
    suspend fun saveObjects(newObjects: List<AuthObject>)

    fun getObjects(): Flow<List<AuthObject>>
}

class InMemoryAuthStorage : AuthStorage {
    private val storedObjects = MutableStateFlow(emptyList<AuthObject>())

    override suspend fun saveObjects(newObjects: List<AuthObject>) {
        storedObjects.value = newObjects
    }

    override fun getObjects(): Flow<List<AuthObject>> = storedObjects
}
