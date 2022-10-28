package com.ilein.cosmodroid.feature_news_list.data.model

import com.ilein.cosmodroid.feature_news_list.domain.model.NewsModel

fun NewsResponce.toNewsPreviewModel() =
    NewsModel(
        id = id,
        date = date,
        description = description,
        featureImage = featureImage,
        type = type,
        name = name
    )