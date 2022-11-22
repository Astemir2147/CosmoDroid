package com.ilein.cosmodroid.feature_news_list.domain.interactor

import com.ilein.cosmodroid.feature_news_list.domain.repository.NewsRepository
import com.ilein.cosmodroid.feature_news_list.domain.ResultState
import com.ilein.cosmodroid.feature_news_list.domain.model.NewsPreviewModel

class NewsInteractorImpl(private val newsRepository: NewsRepository): NewsInteractor {

    override suspend fun getNewsList(): ResultState<List<NewsPreviewModel>> =
        safeCall { newsRepository.getNewsList() }

    override suspend fun getDetailNews(id: Int): ResultState<NewsPreviewModel> =
        safeCall { newsRepository.getDetailNews(id = id) }

    override suspend fun getNewListByType(typeId: Int): ResultState<List<NewsPreviewModel>> =
        safeCall { newsRepository.getNewListByType(typeId = typeId) }
}