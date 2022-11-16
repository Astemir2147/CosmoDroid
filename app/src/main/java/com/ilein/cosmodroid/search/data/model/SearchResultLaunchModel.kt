package com.ilein.cosmodroid.search.data.model

import com.google.gson.annotations.SerializedName
import com.ilein.cosmodroid.search.domain.model.SearchItemModel

data class SearchResultLaunchModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("image")
    val image: String?
)

fun SearchResultLaunchModel.toSearchItem(enumSearchItem: EnumSearchItems): SearchItemModel {
    val imageUrl = this.image
    return SearchItemModel(null,
        this.id,
        this.name,
        this.description,
        imageUrl,
        enumSearchItem)
}

fun List<SearchResultLaunchModel>.toListSearchItem(enumSearchItem: EnumSearchItems): List<SearchItemModel> {
    return this.map { it.toSearchItem(enumSearchItem) }
}
