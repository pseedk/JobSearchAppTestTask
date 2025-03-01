package ru.pvkovalev.jobsearchapptesttask.domain.model

import ru.pvkovalev.jobsearchapptesttask.utils.Constants.EMPTY_STRING

data class VacancyItem(
    val id: String = UNDEFINED_ID,
    val lookingNumber: Int,
    val title: String,
    val town: String,
    val company: String,
    val experience: String,
    val publishedDate: String,
    val isFavorite: Boolean
) {
    companion object {
        const val UNDEFINED_ID = EMPTY_STRING
    }
}