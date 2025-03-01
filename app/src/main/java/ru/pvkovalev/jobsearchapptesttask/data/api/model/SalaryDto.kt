package ru.pvkovalev.jobsearchapptesttask.data.api.model


import com.google.gson.annotations.SerializedName

data class SalaryDto(
    @SerializedName("full")
    val full: String?,
    @SerializedName("short")
    val short: String?
)