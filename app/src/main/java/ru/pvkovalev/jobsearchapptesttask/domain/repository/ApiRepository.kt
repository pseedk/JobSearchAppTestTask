package ru.pvkovalev.jobsearchapptesttask.domain.repository

import ru.pvkovalev.jobsearchapptesttask.data.api.model.SearchWorkResponseDto
import ru.pvkovalev.jobsearchapptesttask.utils.NetworkResult

interface ApiRepository {

    suspend fun getSearchWorkTestData(): NetworkResult<SearchWorkResponseDto>
}