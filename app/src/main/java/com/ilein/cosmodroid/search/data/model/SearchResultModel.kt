package com.ilein.cosmodroid.search.data.model

import com.google.gson.annotations.SerializedName
import com.ilein.cosmodroid.search.domain.model.SearchItemModel

data class SearchResultModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("image_url")
    val imageUrl: String?
)

fun SearchResultModel.toSearchItem(enumSearchItem: EnumSearchItems): SearchItemModel {
    val imageUrl = this.imageUrl ?: enumSearchItem.imgUrl
    return SearchItemModel(this.id,
        this.id.toString(),
        this.name,
        this.description,
        imageUrl,
        enumSearchItem)
}

fun List<SearchResultModel>.toListSearchItem(enumSearchItem: EnumSearchItems): List<SearchItemModel> {
    return this.map { it.toSearchItem(enumSearchItem) }
}
