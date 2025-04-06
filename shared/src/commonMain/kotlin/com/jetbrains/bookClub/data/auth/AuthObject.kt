package com.jetbrains.bookClub.data.bookClub.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthObject(
    val email: String,
    val password: String,
)
