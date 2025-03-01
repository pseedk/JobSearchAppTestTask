package ru.pvkovalev.jobsearchapptesttask.domain.usecases

import ru.pvkovalev.jobsearchapptesttask.domain.model.VacancyItem
import ru.pvkovalev.jobsearchapptesttask.domain.repository.DbRepository
import javax.inject.Inject

class DeleteVacancyFromFavoriteUseCase @Inject constructor(private val dbRepository: DbRepository) {

    suspend fun invoke(vacancyItem: VacancyItem) = dbRepository.deleteVacancyItem(vacancyItem)
}