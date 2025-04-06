package com.jetbrains.bookClub.data.auth

import com.jetbrains.bookClub.data.bookClub.auth.AuthObject
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlin.coroutines.cancellation.CancellationException

interface AuthApi {
    suspend fun getData(): List<AuthObject>
}

class KtorAuthApi(private val client: HttpClient) : AuthApi {
    companion object {
        private const val API_URL =
            "https://raw.githubusercontent.com/Kotlin/KMP-App-Template-Native/main/list.json"
    }

    override suspend fun getData(): List<AuthObject> {
        return try {
            client.get(API_URL).body()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()

            emptyList()
        }
    }
}