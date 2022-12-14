package com.ilein.cosmodroid.core.api

import com.ilein.cosmodroid.feature_news_list.data.model.NewsModel
import com.ilein.cosmodroid.feature_news_list.data.model.NewsResponce
import com.ilein.cosmodroid.search.data.model.SearchResultAgencyByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultAstronautByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultAstronautModel
import com.ilein.cosmodroid.search.data.model.SearchResultEventByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultLaunchByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultLaunchModel
import com.ilein.cosmodroid.search.data.model.SearchResultModel
import com.ilein.cosmodroid.search.data.model.SearchResultSpaceStationByIdModel
import com.ilein.cosmodroid.search.data.model.SearchResultsModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiRequest {
    @GET("event/")
    suspend fun getNews(): NewsModel

    @GET("event/{id}")
    suspend fun getNewsById(@Path("id") id: Int): NewsResponce

    @GET("event/?")
    suspend fun getNewsByType(@Query("type") typeId: Int): NewsModel

    @GET("event/")
    suspend fun getSearchEvents(@Query("search") search: String): SearchResultsModel<SearchResultModel>

    @GET("event/{id}")
    suspend fun getEventById(@Path("id") id: Int): SearchResultEventByIdModel

    @GET("launch/")
    suspend fun getSearchLaunches(@Query("search") search: String): SearchResultsModel<SearchResultLaunchModel>

    @GET("launch/{id}")
    suspend fun getLaunchById(@Path("id") id: String): SearchResultLaunchByIdModel

    @GET("astronaut/")
    suspend fun getSearchAstronauts(@Query("search") search: String): SearchResultsModel<SearchResultAstronautModel>

    @GET("astronaut/{id}")
    suspend fun getAstronautById(@Path("id") id: Int): SearchResultAstronautByIdModel

    @GET("agencies/")
    suspend fun getSearchAgencies(@Query("search") search: String): SearchResultsModel<SearchResultModel>

    @GET("agencies/{id}")
    suspend fun getAgencyById(@Path("id") id: Int): SearchResultAgencyByIdModel

    @GET("spacestation/")
    suspend fun getSearchSpaceStations(@Query("search") search: String): SearchResultsModel<SearchResultModel>

    @GET("spacestation/{id}")
    suspend fun getSpaceStationById(@Path("id") id: Int): SearchResultSpaceStationByIdModel
}