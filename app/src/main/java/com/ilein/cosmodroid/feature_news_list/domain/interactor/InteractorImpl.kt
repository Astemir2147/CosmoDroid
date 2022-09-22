package com.ilein.cosmodroid.feature_news_list.domain.interactor

import com.ilein.cosmodroid.feature_news_list.data.model.ResultNews
import com.ilein.cosmodroid.feature_news_list.domain.repository.Repository

class InteractorImpl (private val newsRepository: Repository): Interactor {
    override suspend fun getNewsList(): List<ResultNews>{
        return newsRepository.getNewsList().results
    }
}