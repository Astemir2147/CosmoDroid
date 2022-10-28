package com.ilein.cosmodroid.feature_news_list.data.model

import com.google.gson.annotations.SerializedName

data class NewsResponce(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: TypeOfNews,
    @SerializedName("description")
    val description: String,
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
)