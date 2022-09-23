package com.ilein.cosmodroid.search.data.repository

import com.ilein.cosmodroid.search.data.api.RetrofitInstance
import com.ilein.cosmodroid.search.data.model.SearchResultAstronautModel
import com.ilein.cosmodroid.search.data.model.SearchResultsModel
import com.ilein.cosmodroid.search.domain.SearchRepository
import com.ilein.cosmodroid.search.data.model.SearchResultLaunchModel
import com.ilein.cosmodroid.search.data.model.SearchResultModel

class SearchRepositoryImpl: SearchRepository {

    override suspend fun getEventsItems(): SearchResultsModel<SearchResultModel> {
        return RetrofitInstance.apiRequest.getEvents()
    }

    override suspend fun getLaunchesItems(): SearchResultsModel<SearchResultLaunchModel> {
        return RetrofitInstance.apiRequest.getLaunches()
    }

    override suspend fun getAstronautsItems(): SearchResultsModel<SearchResultAstronautModel> {
        return RetrofitInstance.apiRequest.getAstronauts()
    }

    override suspend fun getAgenciesItems(): SearchResultsModel<SearchResultModel> {
        return RetrofitInstance.apiRequest.getAgencies()
    }

    override suspend fun getSpaceStationsItems(): SearchResultsModel<SearchResultModel> {
        return RetrofitInstance.apiRequest.getSpaceStations()
    }
}