package ru.pvkovalev.jobsearchapptesttask.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.pvkovalev.jobsearchapptesttask.R
import ru.pvkovalev.jobsearchapptesttask.navigation.AppNavGraph
import ru.pvkovalev.jobsearchapptesttask.navigation.rememberNavigationState
import ru.pvkovalev.jobsearchapptesttask.presentation.screens.comments.CommentsScreen
import ru.pvkovalev.jobsearchapptesttask.presentation.screens.favorite.FavoriteScreen
import ru.pvkovalev.jobsearchapptesttask.presentation.screens.profile.ProfileScreen
import ru.pvkovalev.jobsearchapptesttask.presentation.screens.responses.ResponsesScreen
import ru.pvkovalev.jobsearchapptesttask.presentation.screens.search.SearchScreen
import ru.pvkovalev.jobsearchapptesttask.presentation.ui.theme.AllVacationsButtonColor
import ru.pvkovalev.jobsearchapptesttask.presentation.ui.theme.Red
import ru.pvkovalev.jobsearchapptesttask.presentation.ui.theme.UnselectedContentColor

@Composable
fun MainScreen() {
    val navigationState = rememberNavigationState()
    val mainScreenViewModel: MainScreenViewModel = hiltViewModel()
    val vacancies = mainScreenViewModel.getVacancies.collectAsState(listOf()).value

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            val navigationItems = listOf(
                NavigationItem.Search,
                NavigationItem.Favorite,
                NavigationItem.Responses,
                NavigationItem.Comments,
                NavigationItem.Profile,
            )
            NavigationBar(
                containerColor = Color.Black,
            ) {
                val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

                navigationItems.forEach { navigationItem ->
                    val selected = navBackStackEntry?.destination?.hierarchy?.any {
                            it.hasRoute(navigationItem.screen::class)
                        }  ?: false

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            when (navigationItem) {
                                NavigationItem.Search -> {
                                    navigationState.navigateToSearch()
                                }

                                NavigationItem.Favorite -> {
                                    navigationState.navigateToFavorite()
                                }

                                NavigationItem.Responses -> {
                                    navigationState.navigateToResponses()
                                }

                                NavigationItem.Comments -> {
                                    navigationState.navigateToComments()
                                }

                                NavigationItem.Profile -> {
                                    navigationState.navigateToProfile()
                                }
                            }
                        },
                        icon = {
                            Box {
                                Icon(
                                    modifier = Modifier
                                        .size(24.dp),
                                    painter = painterResource(id = navigationItem.iconResId),
                                    contentDescription = stringResource(R.string.navigation_icon)
                                )
                                if (navigationItem == NavigationItem.Favorite && vacancies.isNotEmpty()) {
                                    Badge(
                                        modifier = Modifier
                                            .padding(start = 14.dp)
                                            .align(Alignment.TopEnd)
                                            .size(15.dp),
                                        containerColor = Red,
                                        contentColor = Color.White,
                                        content = {
                                            Text(
                                                text = vacancies.size.toString()
                                            )
                                        }
                                    )
                                }
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = AllVacationsButtonColor,
                            selectedTextColor = AllVacationsButtonColor,
                            unselectedIconColor = UnselectedContentColor,
                            unselectedTextColor = UnselectedContentColor,
                            indicatorColor = Color.Transparent
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            searchScreenContent = {
                SearchScreen(
                    paddingValues = paddingValues
                )
            },
            favoriteScreenContent = {
                FavoriteScreen(
                    paddingValues = paddingValues
                )
            },
            profileScreenContent = {
                ProfileScreen()
            },
            responsesScreenContent = {
                ResponsesScreen()
            },
            commentsScreenContent = {
                CommentsScreen()
            }
        )
    }

}