package com.ilein.cosmodroid.search.data.model

import com.google.gson.annotations.SerializedName
import com.ilein.cosmodroid.search.domain.model.SearchItemModel

data class SearchResultLaunchModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String?,
    @SerializedName("launch_service_provider")
    val launchServiceProvider: LaunchServiceProviderShortModel?,
    @SerializedName("status")
    val status: LaunchStatusShortModel?,
    @SerializedName("mission")
    val mission: MissionShortModel?
)

data class LaunchServiceProviderShortModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)

data class LaunchStatusShortModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)

data class MissionShortModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?
)

fun SearchResultLaunchModel.toSearchItem(enumSearchItem: EnumSearchItems): SearchItemModel {
    val imageUrl = this.image
    val description =
        "Status: ${this.status?.name}\nLaunch: ${this.launchServiceProvider?.name}\nMission: ${this.mission?.description}"
    return SearchItemModel(
        null,
        this.id,
        this.name,
        description,
        imageUrl,
        enumSearchItem
    )
}

fun List<SearchResultLaunchModel>.toListSearchItem(enumSearchItem: EnumSearchItems): List<SearchItemModel> {
    return this.map { it.toSearchItem(enumSearchItem) }
}
