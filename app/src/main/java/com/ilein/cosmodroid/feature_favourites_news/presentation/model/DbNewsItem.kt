package com.ilein.cosmodroid.feature_favourites_news.presentation.model

data class DbNewsItem(
    val id: Int,
    val date: String,
    val description: String,
    val featureImage: String,
    val type: String,
    val name: String
)