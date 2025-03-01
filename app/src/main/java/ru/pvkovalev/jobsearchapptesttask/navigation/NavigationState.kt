package ru.pvkovalev.jobsearchapptesttask.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class NavigationState(
    val navHostController: NavHostController
) {

    fun navigateToSearch() {
        navHostController.navigate(Screens.SearchScreen)
    }

    fun navigateToFavorite() {
        navHostController.navigate(Screens.FavoriteScreen)
    }

    fun navigateToProfile() {
        navHostController.navigate(Screens.ProfileScreen)
    }

    fun navigateToResponses() {
        navHostController.navigate(Screens.ResponsesScreen)
    }

    fun navigateToComments() {
        navHostController.navigate(Screens.CommentsScreen)
    }
}

@Composable
fun rememberNavigationState(
    navHostController: NavHostController = rememberNavController()
): NavigationState =
    remember { NavigationState(navHostController) }