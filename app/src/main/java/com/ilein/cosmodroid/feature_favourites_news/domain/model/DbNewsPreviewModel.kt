package com.ilein.cosmodroid.feature_favourites_news.domain.model

data class DbNewsPreviewModel(
    val id: Int,
    val date: String,
    val description: String,
    val featureImage: String,
    val type: String,
    val name: String,
    val url: String,
    val videoUrl: String
)
