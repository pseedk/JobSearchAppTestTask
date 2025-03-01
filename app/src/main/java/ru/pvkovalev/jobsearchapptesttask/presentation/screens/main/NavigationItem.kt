package ru.pvkovalev.jobsearchapptesttask.presentation.screens.main

import ru.pvkovalev.jobsearchapptesttask.R
import ru.pvkovalev.jobsearchapptesttask.navigation.Screens

sealed class NavigationItem(
    val screen: Screens,
    val titleResId: Int,
    val iconResId: Int
) {
    data object Search : NavigationItem(
        screen = Screens.SearchScreen,
        titleResId = R.string.search,
        iconResId = R.drawable.ic_search_work
    )

    data object Favorite : NavigationItem(
        screen = Screens.FavoriteScreen,
        titleResId = R.string.favorite,
        iconResId = R.drawable.ic_unlike
    )

    data object Responses : NavigationItem(
        screen = Screens.ResponsesScreen,
        titleResId = R.string.responses,
        iconResId = R.drawable.ic_responses
    )

    data object Comments : NavigationItem(
        screen = Screens.CommentsScreen,
        titleResId = R.string.comments,
        iconResId = R.drawable.ic_comments
    )

    data object Profile : NavigationItem(
        screen = Screens.ProfileScreen,
        titleResId = R.string.profile,
        iconResId = R.drawable.ic_profile
    )
}
