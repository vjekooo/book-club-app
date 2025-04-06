package com.jetbrains.bookClub.data.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthObject(
    val email: String,
    val password: String,
)

@Serializable
data class User(
    val email: String,
    val username: String,
    val id: String
)

@Serializable
data class LoginResponse(
    val user: User,
    val token: String
)

@Serializable
data class LoginRequest(
    val email: String,
    val password: String
)
