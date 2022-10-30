package com.ilein.cosmodroid.feature_news_list.presentation.state

import com.ilein.cosmodroid.feature_news_list.presentation.model.ErrorModel
import com.ilein.cosmodroid.feature_news_list.presentation.model.NewsItem

sealed class DetailNewsViewState(){
    object Shimmer: DetailNewsViewState()

    data class Error(val error: ErrorModel): DetailNewsViewState()
    data class Content(var news: NewsItem): DetailNewsViewState()
}