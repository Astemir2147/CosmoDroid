package com.ilein.cosmodroid.feature_news_list.presentation

import com.ilein.cosmodroid.feature_news_list.presentation.model.ErrorModel
import com.ilein.cosmodroid.feature_news_list.presentation.model.NewsItem

sealed class NewsViewState {
    object Shimmer: NewsViewState()
    object LoadMore: NewsViewState()
    object Loading: NewsViewState()

    data class Error(val error: ErrorModel): NewsViewState()
    data class Content(var newsList: List<NewsItem> = emptyList()): NewsViewState()
}