package com.ilein.cosmodroid.feature_news_list.presentation

import com.ilein.cosmodroid.feature_news_list.presentation.model.DetailNewsItem
import com.ilein.cosmodroid.feature_news_list.presentation.model.ErrorModel

sealed class DetailNewsViewState(){
    object Shimmer: DetailNewsViewState()

    data class Error(val error: ErrorModel): DetailNewsViewState()
    data class Content(var news: DetailNewsItem): DetailNewsViewState()
}