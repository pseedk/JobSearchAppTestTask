package ru.pvkovalev.jobsearchapptesttask.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    searchScreenContent: @Composable () -> Unit,
    favoriteScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
    responsesScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable () -> Unit
) {

    NavHost(
        navController = navHostController,
        startDestination = Screens.SearchScreen
    ) {

        composable<Screens.SearchScreen> {
            searchScreenContent()
        }

        composable<Screens.FavoriteScreen> {
            favoriteScreenContent()
        }

        composable<Screens.ProfileScreen> {
            profileScreenContent()
        }

        composable<Screens.ResponsesScreen> {
            responsesScreenContent()
        }

        composable<Screens.CommentsScreen> {
            commentsScreenContent()
        }
    }
}