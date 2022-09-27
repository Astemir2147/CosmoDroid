package com.ilein.cosmodroid.feature_news_list.data.repository

import com.ilein.cosmodroid.core.api.ApiRequest
import com.ilein.cosmodroid.feature_news_list.data.model.ResultNews
import com.ilein.cosmodroid.feature_news_list.domain.ResultState
import com.ilein.cosmodroid.feature_news_list.domain.repository.NewsRepository

class NewsRepositoryImpl(private val apiRequest: ApiRequest) : NewsRepository {

    override suspend fun getNewsList(): ResultState<List<ResultNews>> {
        val api = apiRequest.getNews().results
        if (api.isNullOrEmpty()) {
            ResultState.Error(Exception())
        } else {
            return ResultState.Success(data = api)
        }
        return ResultState.Loading
    }
}
