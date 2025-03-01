package ru.pvkovalev.jobsearchapptesttask.presentation.screens.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.pvkovalev.jobsearchapptesttask.domain.model.VacancyItem
import ru.pvkovalev.jobsearchapptesttask.domain.usecases.DeleteVacancyFromFavoriteUseCase
import ru.pvkovalev.jobsearchapptesttask.domain.usecases.GetVacanciesFromFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteScreenViewModel @Inject constructor(
    getVacanciesFromFavoriteUseCase: GetVacanciesFromFavoriteUseCase,
    private val deleteVacancyFromFavoriteUseCase: DeleteVacancyFromFavoriteUseCase
) : ViewModel() {


    val getVacancies = getVacanciesFromFavoriteUseCase.invoke()

    fun deleteVacancy(vacancyItem: VacancyItem) {
        viewModelScope.launch {
            deleteVacancyFromFavoriteUseCase.invoke(vacancyItem)
        }
    }
}