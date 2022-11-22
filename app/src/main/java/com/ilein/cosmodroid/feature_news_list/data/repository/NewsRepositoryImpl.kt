package com.ilein.cosmodroid.feature_news_list.data.repository

import com.ilein.cosmodroid.core.api.ApiRequest
import com.ilein.cosmodroid.feature_news_list.data.model.toNewsPreviewModel
import com.ilein.cosmodroid.feature_news_list.domain.model.NewsPreviewModel
import com.ilein.cosmodroid.feature_news_list.domain.repository.NewsRepository

class NewsRepositoryImpl(private val apiRequest: ApiRequest): NewsRepository {

    override suspend fun getNewsList(): List<NewsPreviewModel> {
        return apiRequest.getNews().results.map { it.toNewsPreviewModel() }
    }

    override suspend fun getDetailNews(id: Int): NewsPreviewModel {
        return apiRequest.getNewsById(id = id).toNewsPreviewModel()
    }

    override suspend fun getNewListByType(typeId: Int): List<NewsPreviewModel> {
        return apiRequest.getNewsByType(typeId = typeId).results.map { it.toNewsPreviewModel() }
    }
}
