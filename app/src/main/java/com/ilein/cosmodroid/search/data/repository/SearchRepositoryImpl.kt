package com.ilein.cosmodroid.search.data.repository

import com.ilein.cosmodroid.core.api.ApiRequest
import com.ilein.cosmodroid.search.data.model.SearchResultAstronautModel
import com.ilein.cosmodroid.search.data.model.SearchResultsModel
import com.ilein.cosmodroid.search.domain.SearchRepository
import com.ilein.cosmodroid.search.data.model.SearchResultLaunchModel
import com.ilein.cosmodroid.search.data.model.SearchResultModel

class SearchRepositoryImpl(private val apiRequest: ApiRequest): SearchRepository {

    override suspend fun getEventsItems(): SearchResultsModel<SearchResultModel> {
        return apiRequest.getEvents()
    }

    override suspend fun getLaunchesItems(): SearchResultsModel<SearchResultLaunchModel> {
        return apiRequest.getLaunches()
    }

    override suspend fun getAstronautsItems(): SearchResultsModel<SearchResultAstronautModel> {
        return apiRequest.getAstronauts()
    }

    override suspend fun getAgenciesItems(): SearchResultsModel<SearchResultModel> {
        return apiRequest.getAgencies()
    }

    override suspend fun getSpaceStationsItems(): SearchResultsModel<SearchResultModel> {
        return apiRequest.getSpaceStations()
    }
}