package ru.pvkovalev.jobsearchapptesttask.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screens {

    @Serializable
    data object SearchScreen : Screens()

    @Serializable
    data object FavoriteScreen : Screens()

    @Serializable
    data object ProfileScreen : Screens()

    @Serializable
    data object ResponsesScreen : Screens()

    @Serializable
    data object CommentsScreen : Screens()

}