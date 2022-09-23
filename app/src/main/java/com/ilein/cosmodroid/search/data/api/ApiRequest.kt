package com.ilein.cosmodroid.search.data.api

import com.ilein.cosmodroid.search.data.model.SearchResultsModel
import retrofit2.http.GET

interface ApiRequest {
    @GET("event/")
    suspend fun getEvents() :SearchResultsModel

    @GET("launch/")
    suspend fun getLaunches() :SearchResultsModel

    @GET("astronaut/")
    suspend fun getAstronauts() :SearchResultsModel

    @GET("agencies/")
    suspend fun getAgencies() :SearchResultsModel

    @GET("spacestation/")
    suspend fun getSpaceStations() :SearchResultsModel
}