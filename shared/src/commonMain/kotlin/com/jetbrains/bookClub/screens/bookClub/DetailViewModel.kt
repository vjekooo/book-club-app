package com.jetbrains.bookClub.screens.bookClub

import com.jetbrains.bookClub.data.bookClub.bookClub.BookClubObject
import com.jetbrains.bookClub.data.bookClub.bookClub.BookClubRepository
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.stateIn
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf

class DetailViewModel(private val bookClubRepository: BookClubRepository) : ViewModel() {
    private val objectId = MutableStateFlow<Int?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    @NativeCoroutinesState
    val museumObject: StateFlow<BookClubObject?> = objectId
        .flatMapLatest {
            val id = it ?: return@flatMapLatest flowOf(null)
            bookClubRepository.getObjectById(id)
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun setId(objectId: Int) {
        this.objectId.value = objectId
    }
}
