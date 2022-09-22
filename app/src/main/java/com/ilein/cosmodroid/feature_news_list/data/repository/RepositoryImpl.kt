package com.ilein.cosmodroid.feature_news_list.data.repository

import com.ilein.cosmodroid.feature_news_list.data.api.RetrofitInstance
import com.ilein.cosmodroid.feature_news_list.data.model.NewsModel
import com.ilein.cosmodroid.feature_news_list.domain.repository.Repository

class RepositoryImpl() : Repository {

    override suspend fun getNewsList(): NewsModel {
        return RetrofitInstance.apiRequest.getNews()
    }
}
