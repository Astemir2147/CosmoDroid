package com.ilein.cosmodroid.feature_news_list.domain.model

import com.ilein.cosmodroid.feature_news_list.data.model.TypeOfNews

data class NewsPreviewModel(
    val id: Int,
    val date: String,
    val description: String,
    val featureImage: String,
    val type: TypeOfNews,
    val name: String,
    val url: String,
    val videoUrl: String
)
