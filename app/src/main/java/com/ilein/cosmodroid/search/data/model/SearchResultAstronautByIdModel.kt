package com.ilein.cosmodroid.search.data.model

import com.google.gson.annotations.SerializedName
import com.ilein.cosmodroid.search.domain.model.SearchItemDetailModel

data class SearchResultAstronautByIdModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_image")
    val imageUrl: String?,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("age")
    val age: Int?,
    @SerializedName("flights_count")
    val flights_count: Int?,
    @SerializedName("nationality")
    val nationality: String?
)

fun SearchResultAstronautByIdModel.toSearchItem(): SearchItemDetailModel {
    val imageUrl = imageUrl
    val fullDesc = "Age: ${this.age}\nFlights count: ${this.flights_count}\nNationality: ${this.nationality}"
    return SearchItemDetailModel(this.id.toString(),
        this.name,
        this.bio,
        imageUrl,
        fullDesc)
}