package com.ilein.cosmodroid.feature_news_list.data.api

import com.ilein.cosmodroid.feature_news_list.data.model.NewsModel
import retrofit2.http.GET

interface ApiRequest {
    @GET("event/")
    suspend fun getNews(): NewsModel
}