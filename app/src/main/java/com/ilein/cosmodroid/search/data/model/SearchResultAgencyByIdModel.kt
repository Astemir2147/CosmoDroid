package com.ilein.cosmodroid.search.data.model

import com.google.gson.annotations.SerializedName
import com.ilein.cosmodroid.search.domain.model.SearchItemDetailModel
import com.ilein.cosmodroid.search.domain.model.SearchItemModel

data class SearchResultAgencyByIdModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val type: String?,
    @SerializedName("type")
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
    val fullDesc = "Founding year: " + this.foundingYear
    return SearchItemDetailModel(this.id.toString(),
        this.name,
        description,
        imageUrl,
        fullDesc)
}