package com.jetbrains.bookClub

import com.jetbrains.bookClub.data.bookClub.bookClub.BookClubRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class KoinDependencies : KoinComponent {
    val bookClubRepository: BookClubRepository by inject()
}
