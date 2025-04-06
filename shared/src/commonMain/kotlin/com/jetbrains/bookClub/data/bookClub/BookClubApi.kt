package com.jetbrains.bookClub.data.bookClub.bookClub

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlin.coroutines.cancellation.CancellationException

interface BookClubApi {
    suspend fun getData(): List<BookClubObject>
}

class KtorBookClubApi(private val client: HttpClient) : BookClubApi {
    companion object {
        private const val API_URL =
            "https://raw.githubusercontent.com/Kotlin/KMP-App-Template-Native/main/list.json"
    }

    override suspend fun getData(): List<BookClubObject> {
        return try {
            client.get(API_URL).body()
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            e.printStackTrace()

            emptyList()
        }
    }
}