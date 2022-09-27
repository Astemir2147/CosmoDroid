package com.ilein.cosmodroid.feature_news_list.domain.repository

import com.ilein.cosmodroid.feature_news_list.data.model.ResultNews
import com.ilein.cosmodroid.feature_news_list.domain.ResultState

interface NewsRepository {
    suspend fun getNewsList(): ResultState<List<ResultNews>>
}
