package com.ilein.cosmodroid.feature_news_list.presentation.model

import com.ilein.cosmodroid.feature_news_list.domain.model.DetailNewsModel
import com.ilein.cosmodroid.feature_news_list.domain.model.NewsPreviewModel

fun NewsPreviewModel.toNewsItem() =
    NewsItem(
        id = id,
        date = date,
        description = description,
        featureImage = featureImage,
        type = type
    )

fun DetailNewsModel.toDetailNewsItem() = DetailNewsItem(
    id = id,
    url = url,
    slug = slug,
    name = name,
    updates = updates,
    type = type,
    description = description,
    webcastLive = webcastLive,
    location = location,
    newsUrl = newsUrl,
    videoUrl = videoUrl,
    featureImage = featureImage,
    date = date,
    launches = launches,
    expeditions = expeditions,
    spaceStations = spaceStations,
    program = program
)