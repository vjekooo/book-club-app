package com.jetbrains.bookClub.data.bookClub.bookClub

import kotlinx.serialization.Serializable

@Serializable
data class BookClubObject(
    val objectID: Int,
    val title: String,
    val artistDisplayName: String,
    val medium: String,
    val dimensions: String,
    val objectURL: String,
    val objectDate: String,
    val primaryImage: String,
    val primaryImageSmall: String,
    val repository: String,
    val department: String,
    val creditLine: String,
)
