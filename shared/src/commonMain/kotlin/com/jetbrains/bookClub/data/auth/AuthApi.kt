package com.jetbrains.bookClub.data.auth

import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.client.statement.bodyAsText
import io.ktor.http.*
import kotlinx.serialization.json.Json

interface AuthApi {
    suspend fun login(email: String, password: String): Result<LoginResponse>
}

class KtorAuthApi(private val client: HttpClient) :
    AuthApi {
    companion object {
        private const val API_URL =
            "http://book-api-aqrt4f-843d39-188-245-216-227.traefik.me/auth"
    }

    override suspend fun login(email: String, password: String): Result<LoginResponse> {

        return try {
            val response = client.post("$API_URL/login") {
                contentType(ContentType.Application.Json)
                setBody(LoginRequest(email = email, password = password))
            }

            when (response.status) {
                HttpStatusCode.OK -> {
                    val responseText = response.bodyAsText()
                    val loginResponse = Json.decodeFromString<LoginResponse>(responseText)
                    Result.success(loginResponse)
                }
                else -> Result.failure(Exception("Login failed: ${response.status}"))
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
