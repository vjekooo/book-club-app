package com.jetbrains.bookClub

import android.app.Application
import com.jetbrains.bookClub.di.initKoin
import com.jetbrains.bookClub.screens.DetailViewModel
import com.jetbrains.bookClub.screens.ListViewModel
import org.koin.dsl.module

class BookClubApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(
            listOf(
                module {
                    factory { ListViewModel(get()) }
                    factory { DetailViewModel(get()) }
                }
            )
        )
    }
}
