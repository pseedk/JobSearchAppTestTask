package ru.pvkovalev.jobsearchapptesttask.presentation.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ru.pvkovalev.jobsearchapptesttask.data.api.model.SearchWorkResponseDto
import ru.pvkovalev.jobsearchapptesttask.domain.model.VacancyItem
import ru.pvkovalev.jobsearchapptesttask.domain.usecases.AddVacancyToFavoriteUseCase
import ru.pvkovalev.jobsearchapptesttask.domain.usecases.DeleteVacancyFromFavoriteUseCase
import ru.pvkovalev.jobsearchapptesttask.domain.usecases.GetJobSearchAppDataUseCase
import ru.pvkovalev.jobsearchapptesttask.domain.usecases.GetVacanciesFromFavoriteUseCase
import ru.pvkovalev.jobsearchapptesttask.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val getJobSearchAppDataUseCase: GetJobSearchAppDataUseCase,
    getVacanciesFromFavoriteUseCase: GetVacanciesFromFavoriteUseCase,
    private val addVacancyToFavoriteUseCase: AddVacancyToFavoriteUseCase,
    private val deleteVacancyFromFavoriteUseCase: DeleteVacancyFromFavoriteUseCase
) : ViewModel() {

    private val _searchWorkData =
        MutableStateFlow<NetworkResult<SearchWorkResponseDto>>(NetworkResult.Loading())
    val searchWorkData = _searchWorkData.asStateFlow()

    private val _searchScreenState =
        MutableStateFlow<SearchScreenState>(SearchScreenState.SearchState)
    val searchScreenState = _searchScreenState.asStateFlow()

    val getVacancies = getVacanciesFromFavoriteUseCase.invoke()

    init {
        getSearchWorkTestData()
    }

    private fun getSearchWorkTestData() {
        viewModelScope.launch {
            val response = getJobSearchAppDataUseCase.invoke()
            _searchWorkData.value = response
        }
    }

    fun changeSearchScreenState() {
        val currentState = _searchScreenState.value
        if (currentState is SearchScreenState.SearchState) {
            _searchScreenState.value = SearchScreenState.OpenListState
        } else {
            _searchScreenState.value = SearchScreenState.SearchState
        }
    }

    fun deleteVacancy(vacancyItem: VacancyItem) {
        viewModelScope.launch {
            deleteVacancyFromFavoriteUseCase.invoke(vacancyItem)
        }
    }

    fun addVacancy(vacancyItem: VacancyItem) {
        viewModelScope.launch {
            addVacancyToFavoriteUseCase.invoke(vacancyItem)
        }
    }
}