package ru.pvkovalev.jobsearchapptesttask.data.api

import ru.pvkovalev.jobsearchapptesttask.data.api.ApiService
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService)  {

    suspend fun getSearchWorkTestData() = apiService.getSearchWorkTestData()
}