package ru.pvkovalev.jobsearchapptesttask.presentation.screens.main

import androidx.lifecycle.ViewModel
import ru.pvkovalev.jobsearchapptesttask.domain.usecases.GetVacanciesFromFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    getVacanciesFromFavoriteUseCase: GetVacanciesFromFavoriteUseCase
) : ViewModel() {


    val getVacancies = getVacanciesFromFavoriteUseCase.invoke()

}