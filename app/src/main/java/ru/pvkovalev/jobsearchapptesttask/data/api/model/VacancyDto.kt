package ru.pvkovalev.jobsearchapptesttask.data.api.model


import com.google.gson.annotations.SerializedName
import ru.pvkovalev.jobsearchapptesttask.data.api.model.AddressDto
import ru.pvkovalev.jobsearchapptesttask.data.api.model.ExperienceDto
import ru.pvkovalev.jobsearchapptesttask.data.api.model.SalaryDto

data class VacancyDto(
    @SerializedName("address")
    val addressDto: AddressDto?,
    @SerializedName("appliedNumber")
    val appliedNumber: Int?,
    @SerializedName("company")
    val company: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("experience")
    val experienceDto: ExperienceDto?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("isFavorite")
    val isFavorite: Boolean?,
    @SerializedName("lookingNumber")
    val lookingNumber: Int?,
    @SerializedName("publishedDate")
    val publishedDate: String?,
    @SerializedName("questions")
    val questions: List<String?>?,
    @SerializedName("responsibilities")
    val responsibilities: String?,
    @SerializedName("salary")
    val salaryDto: SalaryDto?,
    @SerializedName("schedules")
    val schedules: List<String?>?,
    @SerializedName("title")
    val title: String?
)