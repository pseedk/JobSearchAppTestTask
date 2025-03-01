package ru.pvkovalev.jobsearchapptesttask.data.api.model


import com.google.gson.annotations.SerializedName

data class SearchWorkResponseDto(
    @SerializedName("offers")
    val offers: List<OfferDto>?,
    @SerializedName("vacancies")
    val vacancies: List<VacancyDto>?
)