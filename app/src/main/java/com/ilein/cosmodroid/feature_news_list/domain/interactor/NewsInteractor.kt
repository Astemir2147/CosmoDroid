package com.ilein.cosmodroid.feature_news_list.domain.interactor

import com.ilein.cosmodroid.feature_news_list.domain.ResultState
import com.ilein.cosmodroid.feature_news_list.domain.model.NewsPreviewModel

interface NewsInteractor {
    suspend fun getNewsList(): ResultState<List<NewsPreviewModel>>
    suspend fun getDetailNews(id:Int): ResultState<NewsPreviewModel>
    suspend fun getNewListByType(typeId: Int): ResultState<List<NewsPreviewModel>>
}