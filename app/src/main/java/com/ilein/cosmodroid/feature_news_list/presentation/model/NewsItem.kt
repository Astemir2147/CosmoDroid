package com.ilein.cosmodroid.feature_news_list.presentation.model

import com.ilein.cosmodroid.feature_news_list.data.model.TypeOfNews

data class NewsItem(
    val id: Int,
    val date: String,
    val description: String,
    val featureImage: String,
    val type: TypeOfNews,
    val name: String,
)