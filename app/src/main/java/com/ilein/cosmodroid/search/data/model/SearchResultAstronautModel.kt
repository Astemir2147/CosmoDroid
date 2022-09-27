package com.ilein.cosmodroid.search.data.model

import com.google.gson.annotations.SerializedName
import com.ilein.cosmodroid.search.domain.model.SearchItemModel

data class SearchResultAstronautModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("bio")
    val bio: String?,
    @SerializedName("profile_image")
    val profileImage: String?
)

fun SearchResultAstronautModel.toSearchItem(enumSearchItem: EnumSearchItems): SearchItemModel {
    val imageUrl = this.profileImage ?: enumSearchItem.imgUrl
    return SearchItemModel(this.id,
        this.id.toString(),
        this.name,
        this.bio,
        imageUrl,
        enumSearchItem)
}

fun List<SearchResultAstronautModel>.toListSearchItem(enumSearchItem: EnumSearchItems): List<SearchItemModel> {
    return this.map { it.toSearchItem(enumSearchItem) }
}