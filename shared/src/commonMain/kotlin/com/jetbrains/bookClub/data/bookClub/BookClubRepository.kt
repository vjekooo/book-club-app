package com.jetbrains.bookClub.data.bookClub.bookClub

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class BookClubRepository(
    private val bookClubApi: BookClubApi,
    private val bookClubStorage: BookClubStorage
    ) {
    private val scope = CoroutineScope(SupervisorJob())

    fun initialize() {
        scope.launch {
            refresh()
        }
    }

    private suspend fun refresh() {
        val data = bookClubApi.getData()
        println(data)
        bookClubStorage.saveObjects(data)
    }

    fun getObjects(): Flow<List<BookClubObject>> = bookClubStorage.getObjects()

    fun getObjectById(objectId: Int): Flow<BookClubObject?> = bookClubStorage.getObjectById(objectId)
}
