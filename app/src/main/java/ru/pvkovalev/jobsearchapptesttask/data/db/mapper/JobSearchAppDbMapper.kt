package ru.pvkovalev.jobsearchapptesttask.data.db.mapper

import ru.pvkovalev.jobsearchapptesttask.data.db.model.VacancyEntity
import ru.pvkovalev.jobsearchapptesttask.domain.model.VacancyItem
import javax.inject.Inject

class JobSearchAppDbMapper @Inject constructor() {

    fun mapVacancyEntityToVacancyItem(vacancyItem: VacancyItem) =
        VacancyEntity(
            id = vacancyItem.id,
            lookingNumber = vacancyItem.lookingNumber,
            title = vacancyItem.title,
            town = vacancyItem.town,
            company = vacancyItem.company,
            experience = vacancyItem.experience,
            publishedDate = vacancyItem.publishedDate,
            isFavorite = vacancyItem.isFavorite
        )

    private fun mapVacancyItemToVacancyEntity(vacancyEntity: VacancyEntity) =
        VacancyItem(
            id = vacancyEntity.id,
            lookingNumber = vacancyEntity.lookingNumber,
            title = vacancyEntity.title,
            town = vacancyEntity.town,
            company = vacancyEntity.company,
            experience = vacancyEntity.experience,
            publishedDate = vacancyEntity.publishedDate,
            isFavorite = vacancyEntity.isFavorite
        )

    fun mapListVacancyItemToListVacancyEntity(list: List<VacancyEntity>) =
        list.map {
            mapVacancyItemToVacancyEntity(it)
        }
}