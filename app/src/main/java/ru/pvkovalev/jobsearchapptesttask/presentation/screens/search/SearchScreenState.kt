package ru.pvkovalev.jobsearchapptesttask.presentation.screens.search

sealed class SearchScreenState {

    data object SearchState : SearchScreenState()

    data object OpenListState : SearchScreenState()
}