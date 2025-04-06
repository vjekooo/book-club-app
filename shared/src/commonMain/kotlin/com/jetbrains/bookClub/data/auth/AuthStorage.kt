package com.jetbrains.bookClub.data.auth

import com.jetbrains.bookClub.data.bookClub.auth.AuthObject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

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
