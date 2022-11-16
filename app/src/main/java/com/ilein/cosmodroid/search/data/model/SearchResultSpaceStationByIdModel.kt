package com.ilein.cosmodroid.search.data.model

import com.google.gson.annotations.SerializedName
import com.ilein.cosmodroid.search.domain.model.SearchItemDetailModel

data class SearchResultSpaceStationByIdModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("founded")
    val founded: String?,
    @SerializedName("orbit")
    val orbit: String?
)

fun SearchResultSpaceStationByIdModel.toSearchItem(): SearchItemDetailModel {
    val imageUrl = imageUrl
    val fullDesc = "Founding year: ${this.founded}\nOrbit: ${orbit}"
    return SearchItemDetailModel(this.id.toString(),
        this.name,
        description,
        imageUrl,
        fullDesc)
}