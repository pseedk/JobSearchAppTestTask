package ru.pvkovalev.jobsearchapptesttask.domain.usecases

import ru.pvkovalev.jobsearchapptesttask.domain.repository.DbRepository
import javax.inject.Inject

class GetVacanciesFromFavoriteUseCase @Inject constructor(private val dbRepository: DbRepository) {

    fun invoke() = dbRepository.getVacancies()
}