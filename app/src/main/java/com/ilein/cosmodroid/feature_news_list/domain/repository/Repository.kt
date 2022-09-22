package com.ilein.cosmodroid.feature_news_list.domain.repository

import com.ilein.cosmodroid.feature_news_list.data.model.NewsModel

interface Repository {
    suspend fun getNewsList(): NewsModel
}
