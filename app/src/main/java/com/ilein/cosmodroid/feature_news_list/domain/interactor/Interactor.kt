package com.ilein.cosmodroid.feature_news_list.domain.interactor

import com.ilein.cosmodroid.feature_news_list.data.model.ResultNews

interface Interactor {
    suspend fun getNewsList(): List<ResultNews>
}