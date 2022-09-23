package com.ilein.cosmodroid.search.data.api

import com.ilein.cosmodroid.search.data.model.SearchResultAstronautModel
import com.ilein.cosmodroid.search.data.model.SearchResultLaunchModel
import com.ilein.cosmodroid.search.data.model.SearchResultModel
import com.ilein.cosmodroid.search.data.model.SearchResultsModel
import retrofit2.http.GET

interface ApiRequest {
    @GET("event/")
    suspend fun getEvents() :SearchResultsModel<SearchResultModel>

    @GET("launch/")
    suspend fun getLaunches() :SearchResultsModel<SearchResultLaunchModel>

    @GET("astronaut/")
    suspend fun getAstronauts() :SearchResultsModel<SearchResultAstronautModel>

    @GET("agencies/")
    suspend fun getAgencies() :SearchResultsModel<SearchResultModel>

    @GET("spacestation/")
    suspend fun getSpaceStations() :SearchResultsModel<SearchResultModel>
}