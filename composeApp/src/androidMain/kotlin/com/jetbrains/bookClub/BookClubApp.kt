package com.jetbrains.bookClub

import LoginViewModel
import android.app.Application
import com.jetbrains.bookClub.di.initKoin
import com.jetbrains.bookClub.screens.bookClub.BookClubDetailViewModel
import com.jetbrains.bookClub.screens.bookClub.BookClubListViewModel
import org.koin.dsl.module

class BookClubApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            listOf(
                module {
                    factory { BookClubListViewModel(get()) }
                    factory { BookClubDetailViewModel(get()) }
                    factory { LoginViewModel(get()) }
                }
            )
        )
    }
}
