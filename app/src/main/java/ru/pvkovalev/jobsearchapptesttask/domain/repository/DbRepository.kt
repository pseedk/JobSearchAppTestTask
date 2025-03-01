package ru.pvkovalev.jobsearchapptesttask.domain.repository

import ru.pvkovalev.jobsearchapptesttask.domain.model.VacancyItem
import kotlinx.coroutines.flow.Flow

interface DbRepository {

    fun getVacancies(): Flow<List<VacancyItem>>

    suspend fun addVacancyItem(vacancyItem: VacancyItem)

    suspend fun deleteVacancyItem(vacancyItem: VacancyItem)
}