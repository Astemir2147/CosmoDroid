package com.ilein.cosmodroid.feature_news_list.data.model

data class NewsModel(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<ResultNews>
)