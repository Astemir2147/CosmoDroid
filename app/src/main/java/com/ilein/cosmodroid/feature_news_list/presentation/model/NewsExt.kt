package com.ilein.cosmodroid.feature_news_list.presentation.model

import com.ilein.cosmodroid.feature_news_list.domain.model.NewsPreviewModel

fun NewsPreviewModel.toNewsItem() =
    NewsItem(
        id = id,
        date = date,
        description = description,
        feature_image = feature_image,
        type = type
    )