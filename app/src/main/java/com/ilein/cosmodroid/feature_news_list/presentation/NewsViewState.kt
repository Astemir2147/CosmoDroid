package com.ilein.cosmodroid.feature_news_list.presentation

import com.ilein.cosmodroid.feature_news_list.data.model.ResultNews

sealed class NewsViewState {
    object Shimmer : NewsViewState()
    object LoadMore : NewsViewState()

    data class Error(val error: Exception) : NewsViewState()
    data class Content(var newsList: List<ResultNews> = emptyList()) : NewsViewState()
}