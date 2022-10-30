package com.ilein.cosmodroid.feature_favourites_news.presentation.model

import com.ilein.cosmodroid.feature_favourites_news.domain.model.DbNewsPreviewModel
import com.ilein.cosmodroid.feature_news_list.presentation.model.NewsItem

fun DbNewsPreviewModel.dbNewsToDbNewsItem() =
    DbNewsItem(
        id = id,
        date = date,
        description = description,
        featureImage = featureImage,
        type = type,
        name = name
    )

fun DbNewsItem.dbNewsToNewsItem() =
    NewsItem(
        id = id,
        date = date,
        description = description,
        featureImage = featureImage,
        type = type,
        name = name
    )