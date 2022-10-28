package com.ilein.cosmodroid.feature_news_list.presentation.model

import com.ilein.cosmodroid.feature_news_list.domain.model.NewsModel

fun NewsModel.toNewsItem() =
    NewsItem(
        id = id,
        date = date,
        description = description,
        featureImage = featureImage,
        type = type,
        name = name,
    )