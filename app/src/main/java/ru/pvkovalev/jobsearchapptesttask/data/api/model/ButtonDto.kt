package ru.pvkovalev.jobsearchapptesttask.data.api.model


import com.google.gson.annotations.SerializedName

data class ButtonDto(
    @SerializedName("text")
    val text: String?
)