package com.ilein.cosmodroid.search.data.model

import com.google.gson.annotations.SerializedName
import com.ilein.cosmodroid.search.domain.model.SearchItemDetailModel

data class SearchResultLaunchByIdModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?,
    @SerializedName("image")
    val imageUrl: String?,
    @SerializedName("launch_service_provider")
    val launchServiceProvider: LaunchServiceProviderModel?,
    @SerializedName("status")
    val status: LaunchStatusModel?
)

data class LaunchServiceProviderModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String?
)

data class LaunchStatusModel(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
)

fun SearchResultLaunchByIdModel.toSearchItem(): SearchItemDetailModel {
    val imageUrl = imageUrl
    val fullDesc = launchServiceProvider?.description
    val description = status?.name
    return SearchItemDetailModel(this.id,
        this.name,
        description,
        imageUrl,
        fullDesc)
}