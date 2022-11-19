package com.ilein.cosmodroid.feature_news_list.presentation.model

import java.io.Serializable

data class NewsItem(
    val id: Int,
    val date: String,
    val description: String,
    val featureImage: String,
    val type: String,
    val name: String,
    val url: String,
    val videoUrl: String
): Serializable