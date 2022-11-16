package com.ilein.cosmodroid.core.api

import com.ilein.cosmodroid.feature_news_list.data.model.NewsModel
import com.ilein.cosmodroid.feature_news_list.data.model.NewsResponce
import com.ilein.cosmodroid.search.data.model.SearchResultAgencyByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultAstronautModel
import com.ilein.cosmodroid.search.data.model.SearchResultLaunchModel
import com.ilein.cosmodroid.search.data.model.SearchResultModel
import com.ilein.cosmodroid.search.data.model.SearchResultsModel
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiRequest {
    @GET("event/")
    suspend fun getNews(): NewsModel

    @GET("event/{id}")
    suspend fun getNewsById(@Path("id") id: Int): NewsResponce

    @GET("event/")
    suspend fun getEvents(): SearchResultsModel<SearchResultModel>

    @GET("launch/")
    suspend fun getLaunches(): SearchResultsModel<SearchResultLaunchModel>

    @GET("astronaut/")
    suspend fun getAstronauts(): SearchResultsModel<SearchResultAstronautModel>

    @GET("agencies/")
    suspend fun getAgencies(): SearchResultsModel<SearchResultModel>

    @GET("agencies/{id}")
    suspend fun getAgenciesById(@Path("id") id: Int): SearchResultAgencyByIdModel

    @GET("spacestation/")
    suspend fun getSpaceStations(): SearchResultsModel<SearchResultModel>
}