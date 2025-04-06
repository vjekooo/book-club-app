package com.jetbrains.bookClub.data.auth

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

interface AuthStorage {
    suspend fun saveObjects(newObjects: LoginResponse)

    fun getObjects(): Flow<LoginResponse>
}

class InMemoryAuthStorage : AuthStorage {
    private val storedObjects = MutableStateFlow(LoginResponse(user = User("", "", id = ""), token = ""))

    override suspend fun saveObjects(newObjects: LoginResponse) {
        storedObjects.value = newObjects
    }

    override fun getObjects(): Flow<LoginResponse> = storedObjects

    fun clear() {
        storedObjects.value = LoginResponse(user = User("", "", id = ""), token = "")
    }
}

