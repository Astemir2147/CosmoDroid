package com.ilein.cosmodroid.feature_news_list.data.model

import com.ilein.cosmodroid.feature_news_list.domain.model.NewsPreviewModel

fun NewsResponce.toNewsPreviewModel() =
    NewsPreviewModel(
        id = id,
        date = date,
        description = description,
        featureImage = featureImage,
        type = type,
        name = name
    )