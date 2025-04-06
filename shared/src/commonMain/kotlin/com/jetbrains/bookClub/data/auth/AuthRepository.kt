package com.jetbrains.bookClub.data.auth

import com.jetbrains.bookClub.data.bookClub.auth.AuthObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class AuthRepository(
    private val api: AuthApi,
    private val storage: AuthStorage
    ) {
    private val scope = CoroutineScope(SupervisorJob())

    fun initialize() {
        scope.launch {
            refresh()
        }
    }

    private suspend fun refresh() {
        val data = api.getData()
        println(data)
        storage.saveObjects(data)
    }

    fun getObjects(): Flow<List<AuthObject>> = storage.getObjects()
}
