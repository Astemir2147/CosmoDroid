package com.ilein.cosmodroid.feature_news_list.presentation.state

import com.ilein.cosmodroid.feature_news_list.presentation.model.ErrorModel
import com.ilein.cosmodroid.feature_news_list.presentation.model.NewsItem

sealed class NewsViewState {
    object Shimmer: NewsViewState()

    data class Error(val error: ErrorModel): NewsViewState()
    data class Content(var newsList: List<NewsItem> = emptyList()): NewsViewState()
}