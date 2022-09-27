package com.ilein.cosmodroid.feature_news_list.domain.interactor

import com.ilein.cosmodroid.feature_news_list.data.model.ResultNews
import com.ilein.cosmodroid.feature_news_list.domain.repository.NewsRepository
import com.ilein.cosmodroid.feature_news_list.domain.ResultState

class NewsInteractorImpl(private val newsRepository: NewsRepository) : NewsInteractor {
    override suspend fun getNewsList():ResultState<List<ResultNews>>  {
        return newsRepository.getNewsList()
    }
}