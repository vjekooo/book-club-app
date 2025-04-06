package com.jetbrains.bookClub.screens.bookClub

import com.jetbrains.bookClub.data.bookClub.bookClub.BookClubObject
import com.jetbrains.bookClub.data.bookClub.bookClub.BookClubRepository
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.stateIn
import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow

class ListViewModel(bookClubRepository: BookClubRepository) : ViewModel() {
    @NativeCoroutinesState
    val objects: StateFlow<List<BookClubObject>> =
        bookClubRepository.getObjects()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
}
