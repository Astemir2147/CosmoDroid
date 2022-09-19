package com.ilein.cosmodroid.feature_news_list.data.model

data class EventModel(
    val date: String,
    val description: String,
    val expeditions: List<Any>,
    val feature_image: String,
    val id: Int,
    val launches: List<Any>,
    val location: String,
    val name: String,
    val news_url: String,
    val program: List<Any>,
    val slug: String,
    val spacestations: List<Any>,
    val type: String,
    val updates: List<Any>,
    val url: String,
    val video_url: String,
    val webcast_live: Boolean
)