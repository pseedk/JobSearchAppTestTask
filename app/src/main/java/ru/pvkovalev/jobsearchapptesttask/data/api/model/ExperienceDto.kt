package ru.pvkovalev.jobsearchapptesttask.data.api.model


import com.google.gson.annotations.SerializedName

data class ExperienceDto(
    @SerializedName("previewText")
    val previewText: String?,
    @SerializedName("text")
    val text: String?
)