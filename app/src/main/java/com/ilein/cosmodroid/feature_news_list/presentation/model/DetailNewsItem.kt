package com.ilein.cosmodroid.feature_news_list.presentation.model

import com.ilein.cosmodroid.feature_news_list.data.model.TypeOfNews

data class DetailNewsItem(
    val id: Int,
    val url: String,
    val slug: String,
    val name: String,
    val updates: List<Any>,
    val type: TypeOfNews,
    val description: String,
    val webcastLive: Boolean,
    val location: String,
    val newsUrl: String,
    val videoUrl: String,
    val featureImage: String,
    val date: String,
    val launches: List<Any>,
    val expeditions: List<Any>,
    val spaceStations: List<Any>,
    val program: List<Any>
)