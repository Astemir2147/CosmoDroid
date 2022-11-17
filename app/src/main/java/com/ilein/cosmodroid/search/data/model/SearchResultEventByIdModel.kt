package com.ilein.cosmodroid.search.data.model

import com.google.gson.annotations.SerializedName
import com.ilein.cosmodroid.search.domain.model.SearchItemDetailModel

data class SearchResultEventByIdModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("date")
    val date: String?,
    @SerializedName("location")
    val location: String?
)

fun SearchResultEventByIdModel.toSearchItem(): SearchItemDetailModel {
    val imageUrl = imageUrl
    val fullDesc = "Location: ${this.location}\nDate: ${this.date}"
    return SearchItemDetailModel(this.id.toString(),
        this.name,
        description,
        imageUrl,
        fullDesc)
}