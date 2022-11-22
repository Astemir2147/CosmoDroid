package com.ilein.cosmodroid.feature_news_list.domain.repository

import com.ilein.cosmodroid.feature_news_list.domain.model.NewsPreviewModel

interface NewsRepository {
    suspend fun getNewsList(): List<NewsPreviewModel>
    suspend fun getDetailNews(id:Int): NewsPreviewModel
    suspend fun getNewListByType(typeId: Int): List<NewsPreviewModel>
}
