package com.jetbrains.bookClub

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.jetbrains.bookClub.screens.DetailScreen
import com.jetbrains.bookClub.screens.HomeScreen
import com.jetbrains.bookClub.screens.ListScreen
import com.jetbrains.bookClub.screens.LoginScreen
import kotlinx.serialization.Serializable

@Serializable
object HomeDestination

@Serializable
object LoginDestination

@Serializable
object ListDestination

@Serializable
data class DetailDestination(val objectId: Int)

@Composable
fun App() {
    MaterialTheme(
        colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()
    ) {
        Surface {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = HomeDestination) {
                composable<HomeDestination> {
                    HomeScreen(
                        onNavigateToList = {
                            navController.navigate(ListDestination) {
                                popUpTo(HomeDestination) { inclusive = true }
                            }
                        }

                    )
                }
                composable<ListDestination> {
                    ListScreen(navigateToDetails = { objectId ->
                        navController.navigate(DetailDestination(objectId))
                    })
                }
                composable<DetailDestination> { backStackEntry ->
                    DetailScreen(
                        objectId = backStackEntry.toRoute<DetailDestination>().objectId,
                        navigateBack = {
                            navController.popBackStack()
                        }
                    )
                }
            }
        }
    }
}
