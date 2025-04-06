package com.jetbrains.bookClub.screens

import androidx.compose.runtime.*

@Composable
fun HomeScreen(
    onNavigateToList: () -> Unit
) {
    var isAuthenticated by remember { mutableStateOf(false) }

    LaunchedEffect(isAuthenticated) {
        if (isAuthenticated) {
            onNavigateToList()
        }
    }

    LoginScreen(
        onLoginSuccess = { isAuthenticated = true }
    )
}
