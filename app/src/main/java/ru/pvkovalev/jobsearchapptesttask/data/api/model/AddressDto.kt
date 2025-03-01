package ru.pvkovalev.jobsearchapptesttask.data.api.model


import com.google.gson.annotations.SerializedName

data class AddressDto(
    @SerializedName("house")
    val house: String?,
    @SerializedName("street")
    val street: String?,
    @SerializedName("town")
    val town: String?
)