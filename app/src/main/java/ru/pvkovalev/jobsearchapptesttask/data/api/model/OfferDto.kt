package ru.pvkovalev.jobsearchapptesttask.data.api.model


import com.google.gson.annotations.SerializedName
import ru.pvkovalev.jobsearchapptesttask.data.api.model.ButtonDto

data class OfferDto(
    @SerializedName("button")
    val buttonDto: ButtonDto?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("link")
    val link: String?,
    @SerializedName("title")
    val title: String?
)