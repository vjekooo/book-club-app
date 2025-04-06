package com.jetbrains.bookClub.di

import com.jetbrains.bookClub.data.auth.AuthApi
import com.jetbrains.bookClub.data.auth.AuthRepository
import com.jetbrains.bookClub.data.auth.KtorAuthApi
import com.jetbrains.bookClub.data.auth.AuthStorage
import com.jetbrains.bookClub.data.auth.InMemoryAuthStorage
import com.jetbrains.bookClub.data.bookClub.bookClub.InMemoryBookClubStorage
import com.jetbrains.bookClub.data.bookClub.bookClub.KtorBookClubApi
import com.jetbrains.bookClub.data.bookClub.bookClub.BookClubApi
import com.jetbrains.bookClub.data.bookClub.bookClub.BookClubRepository
import com.jetbrains.bookClub.data.bookClub.bookClub.BookClubStorage
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

val dataModule = module {
    single {
        val json = Json { ignoreUnknownKeys = true }
        HttpClient {
            install(ContentNegotiation) {
                // TODO Fix API so it serves application/json
                json(json, contentType = ContentType.Any)
            }
        }
    }

    single<BookClubApi> { KtorBookClubApi(get()) }
    single<BookClubStorage> { InMemoryBookClubStorage() }
    single {
        BookClubRepository(get(), get()).apply {
            initialize()
        }
    }
    single<AuthApi> { KtorAuthApi(get()) }
    single<AuthStorage> { InMemoryAuthStorage() }
    single {
        AuthRepository(get(), get()).apply {
            initialize()
        }
    }
}

fun initKoin() = initKoin(emptyList())

fun initKoin(extraModules: List<Module>) {
    startKoin {
        modules(
            dataModule,
            *extraModules.toTypedArray(),
        )
    }
}
