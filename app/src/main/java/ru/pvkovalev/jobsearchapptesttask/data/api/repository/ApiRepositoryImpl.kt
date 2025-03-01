package ru.pvkovalev.jobsearchapptesttask.data.api.repository

import ru.pvkovalev.jobsearchapptesttask.data.api.RemoteDataSource
import ru.pvkovalev.jobsearchapptesttask.data.api.model.SearchWorkResponseDto
import ru.pvkovalev.jobsearchapptesttask.domain.repository.ApiRepository
import ru.pvkovalev.jobsearchapptesttask.utils.BaseApiResponse
import ru.pvkovalev.jobsearchapptesttask.utils.NetworkResult
import javax.inject.Inject

class ApiRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse(), ApiRepository {

    override suspend fun getSearchWorkTestData(): NetworkResult<SearchWorkResponseDto> =
        safeApiCall { remoteDataSource.getSearchWorkTestData() }
}