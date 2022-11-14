package com.ilein.cosmodroid.feature_favourites_news.data.model

import com.ilein.cosmodroid.core.database.entity.NewsEntity
import com.ilein.cosmodroid.feature_favourites_news.domain.model.DbNewsPreviewModel

fun NewsEntity.toDbPreviewModel() =
    DbNewsPreviewModel(
        id = newsId,
        date = dateOfNewsName,
        type = typeOfNewsName,
        description = newsContent,
        featureImage = imageOfNews,
        name = nameOfNews,
        url = newsUrl
    )