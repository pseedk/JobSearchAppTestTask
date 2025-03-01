package ru.pvkovalev.jobsearchapptesttask.data.api

import ru.pvkovalev.jobsearchapptesttask.data.api.model.SearchWorkResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("u/0/uc")
    suspend fun getSearchWorkTestData(
        @Query("id") id: String = "1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r",
        @Query("export") export: String = "download"
    ) : Response<SearchWorkResponseDto>
}