package com.jetbrains.bookClub.data.auth

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
//            refresh()
        }
    }

    suspend fun onLoginClick(username: String, password: String) {
        val data = api.login(username, password)
        data.getOrElse {
            println("Error during login: ${it.message}")
            return
        }.let { loginResponse ->
            storage.saveObjects(loginResponse)
        }
    }

    //    private suspend fun refresh() {
//        val data = api.login()
//        println(data)
//        storage.saveObjects(data)
//    }
//
    fun getObjects(): Flow<LoginResponse> = storage.getObjects()
}
