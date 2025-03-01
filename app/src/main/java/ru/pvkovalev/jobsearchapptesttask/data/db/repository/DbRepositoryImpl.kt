package ru.pvkovalev.jobsearchapptesttask.data.db.repository

import ru.pvkovalev.jobsearchapptesttask.data.db.SearchWorkDao
import ru.pvkovalev.jobsearchapptesttask.data.db.mapper.JobSearchAppDbMapper
import ru.pvkovalev.jobsearchapptesttask.domain.model.VacancyItem
import ru.pvkovalev.jobsearchapptesttask.domain.repository.DbRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DbRepositoryImpl(
    private val searchWorkDao: SearchWorkDao,
    private val mapper: JobSearchAppDbMapper
) : DbRepository {

    override fun getVacancies(): Flow<List<VacancyItem>> =
        searchWorkDao.getVacancies().map {
            mapper.mapListVacancyItemToListVacancyEntity(it)
        }

    override suspend fun addVacancyItem(vacancyItem: VacancyItem) {
        searchWorkDao.addVacancy(mapper.mapVacancyEntityToVacancyItem(vacancyItem))
    }

    override suspend fun deleteVacancyItem(vacancyItem: VacancyItem) {
        searchWorkDao.deleteVacancy(mapper.mapVacancyEntityToVacancyItem(vacancyItem))
    }
}