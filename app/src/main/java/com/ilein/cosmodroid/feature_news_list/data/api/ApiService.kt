package com.ilein.cosmodroid.feature_news_list.data.api


import androidx.constraintlayout.helper.widget.Carousel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("event")
    suspend fun getCarusel() :Response<Carousel>
}