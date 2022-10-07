package com.ilein.cosmodroid.feature_news_list.data.model

import com.google.gson.annotations.SerializedName

data class NewsResponce(
    @SerializedName("id")
    val id: Int,
    @SerializedName("url")
    val url: String,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("updates")
    val updates: List<Any>,
    @SerializedName("type")
    val type: TypeOfNews,
    @SerializedName("description")
    val description: String,
    @SerializedName("webcast_live")
    val webcastLive: Boolean,
    @SerializedName("location")
    val location: String,
    @SerializedName("news_url")
    val newsUrl: String,
    @SerializedName("video_url")
    val videoUrl: String,
    @SerializedName("feature_image")
    val featureImage: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("launches")
    val launches: List<Any>,
    @SerializedName("expeditions")
    val expeditions: List<Any>,
    @SerializedName("spacestations")
    val spaceStations: List<Any>,
    @SerializedName("program")
    val program: List<Any>
)