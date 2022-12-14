package com.ilein.cosmodroid.search.data.model

import com.google.gson.annotations.SerializedName
import com.ilein.cosmodroid.search.domain.model.SearchItemDetailModel

data class SearchResultAgencyByIdModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("image_url")
    val imageUrl: String?,
    @SerializedName("founding_year")
    val foundingYear: String?,
    @SerializedName("launchers")
    val launchers: String?,
    @SerializedName("info_url")
    val info_url: String?
)

fun SearchResultAgencyByIdModel.toSearchItem(): SearchItemDetailModel {
    val imageUrl = imageUrl
    val fullDesc = "Type: ${this.type}\nFounding year: ${this.foundingYear}\nLaunchers: ${this.launchers}"
    return SearchItemDetailModel(this.id.toString(),
        this.name,
        description,
        imageUrl,
        fullDesc)
}