package com.ilein.cosmodroid.common.modalBottomSheet.data.model

import com.ilein.cosmodroid.common.modalBottomSheet.domain.model.NewsModelBS
import com.ilein.cosmodroid.common.modalBottomSheet.presentation.modal.NewsItemBS
import com.ilein.cosmodroid.core.database.entity.NewsEntity
import com.ilein.cosmodroid.feature_news_list.presentation.model.NewsItem

fun NewsModelBS.toNewsEntity() = NewsEntity(
    newsId = id,
    newsContent = description,
    dateOfNewsName = date,
    typeOfNewsName = type,
    nameOfNews = name,
    imageOfNews = featureImage,
    newsUrl = url,
    videoUrl = videoUrl
)
fun NewsItemBS.toNewsModelBS() =
    NewsModelBS(
        id = id,
        date = date,
        description = description,
        featureImage = featureImage,
        type = type,
        name = name,
        url = url,
        videoUrl = videoUrl
    )
fun NewsItem.toNewsItemBS() =
    NewsItemBS(
        id = id,
        date = date,
        description = description,
        featureImage = featureImage,
        type = type,
        name = name,
        url = url,
        videoUrl = videoUrl
    )