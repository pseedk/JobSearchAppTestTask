package ru.pvkovalev.jobsearchapptesttask.domain.usecases

import ru.pvkovalev.jobsearchapptesttask.data.api.repository.ApiRepositoryImpl
import javax.inject.Inject

class GetJobSearchAppDataUseCase @Inject constructor(private val mainRepository: ApiRepositoryImpl) {

    suspend fun invoke() = mainRepository.getSearchWorkTestData()
}