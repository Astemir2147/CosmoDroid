package com.ilein.cosmodroid.feature_news_list.domain.interactor

import com.ilein.cosmodroid.feature_news_list.data.model.ResultNews
import com.ilein.cosmodroid.feature_news_list.domain.ResultState

interface NewsInteractor {
    suspend fun getNewsList(): ResultState<List<ResultNews>>
}