package com.ilein.cosmodroid.feature_news_list.presentation.model

import com.ilein.cosmodroid.core.database.entity.NewsEntity
import com.ilein.cosmodroid.feature_news_list.domain.model.NewsPreviewModel

fun NewsPreviewModel.toNewsItem() =
    NewsItem(
        id = id,
        date = date,
        description = description,
        featureImage = featureImage,
        type = type.name,
        name = name,
    )

fun NewsItem.toNewsEntity() = NewsEntity(
    newsId = id,
    newsContent = description,
    dateOfNewsName = date,
    typeOfNewsName = type,
    nameOfNews = name,
    imageOfNews = featureImage
)